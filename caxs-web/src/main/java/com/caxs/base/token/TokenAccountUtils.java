package com.caxs.base.token;


import com.caxs.base.domain.HmUser;
import com.caxs.base.domain.TokenUserAccount;
import com.caxs.base.util.SpringBeanUtil;
import com.caxs.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

/**
 * 供其他Controller放入或获取登陆信息
 * Created by lenovo on 2017/7/31.
 */


public class TokenAccountUtils {

    //accessToken定义名称
    private static final String accessTokenName =
            "X-Auth-Token";

    //tokenUserAccount定义名称
    private static final String accessTokenAcountName =
            "tokenUserAccount";

    private static RedisManagerForToken redisManagerForToken;

    static {
        redisManagerForToken = SpringBeanUtil.getBean(RedisManagerForToken.class);
    }


    //TODO  通过该方法从request获取用户账户
    public static TokenUserAccount getUserAccount(HttpServletRequest request) {
        String accessToken = (String) request.getAttribute(accessTokenName);
        if (accessToken == null) {
            accessToken = request.getParameter(accessTokenName);
        }
        if (accessToken == null) {
//            throw new BusinessException(BaseConstant.NO_LOGIN, "没有登录！");
            throw new BusinessException("您的登录已过期,请重新登录");
        }
        TokenUserAccount tokenUserAccount = (TokenUserAccount) redisManagerForToken
                .getCache().get(accessToken);
        if (tokenUserAccount == null) {
//            throw new BusinessException(BaseConstant.NO_LOGIN, "没有登录！");
            throw new BusinessException("您的登录已过期,请重新登录");
        }
        return tokenUserAccount;
    }

    public static TokenUserAccount getUserAccountIfLogin(HttpServletRequest request) {
        String accessToken = (String) request.getAttribute(accessTokenName);
        if (accessToken == null) {
            accessToken = request.getParameter(accessTokenName);
        }
        if (accessToken == null) {
            return null;
        }
        TokenUserAccount tokenUserAccount = (TokenUserAccount) redisManagerForToken
                .getCache().get(accessToken);
        if (tokenUserAccount == null) {
            return null;
        }
        return tokenUserAccount;
    }

    //TODO  通过缓存判断是否登录，如登录则返回TokenUserAccount
    public static TokenUserAccount checkLogin(HttpServletRequest request) {
        return getUserAccount(request);
    }

    //TODO  通过该方法将用户账户放入request
    public static void putUserAccount(HttpServletRequest request, TokenUserAccount o) {
        request.setAttribute(accessTokenName, o);
    }

    //TODO  通过该方法将用户账户放入cache，应当同时记录token-》TokenUserAccount 以及 userNo -》 token 信息
    public static void cacheLoginAccount(TokenUserAccount o) {
        if (o == null) {
            return;
        }
        redisManagerForToken.getCache().put(o.getLoginId(), o.getToken());
        redisManagerForToken.getCache().put(o.getToken(), o);
    }

    public static void cacheLoginUsrMsg(TokenUserAccount o) {
        if (o == null) {
            return;
        }
        //redisManagerForToken.getCache().put(o.getUsrCde(), o.getToken());
        redisManagerForToken.getCache().put(o.getToken(), o);
    }

    //TODO  通过该方法将用户账户从cache中清除，应当同时清除token-》TokenUserAccount 以及 userNo -》 token 信息
    public static void removeLoginAccount(TokenUserAccount o) {
        redisManagerForToken.getCache().remove(o.getToken());
        redisManagerForToken.getCache().remove(o.getLoginId());
    }

    public static void removeLoginUsrMsg(TokenUserAccount o){
        redisManagerForToken.getCache().remove(o.getToken());
       // redisManagerForToken.getCache().remove(o.getUsrCde());
    }

    public static String getToken(HmUser hmUser) {
        String loginid = hmUser.getLoginid();
        return (String) redisManagerForToken.getCache().get(loginid);
    }
}
