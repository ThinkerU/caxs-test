package com.caxs.base.token;

import com.caxs.base.constatnt.Constant;
import com.caxs.base.domain.HmUser;
import com.caxs.base.domain.LoginResult;
import com.caxs.base.domain.TokenUserAccount;
import com.caxs.base.service.ILoginService;
import com.caxs.exception.BusinessException;
import com.caxs.util.DigestUtils;
import com.caxs.util.EncodesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Component
public class TokenGenerator {

    @Autowired
    private ILoginService loginService;

    public TokenUserAccount getTokenUserAccount(HttpServletRequest request){
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        HmUser hmUser;
        LoginResult loginResult = loginService.getLoginResult(loginId, password);
        if(null != loginResult && Constant.LOGIN_STATUS_RIGHT.equals(loginResult.getLoginState())){
            hmUser = loginResult.getHmUser();
            String oldToken = TokenAccountUtils.getToken(hmUser);
            if(null != oldToken){
                request.setAttribute("access_token",oldToken);
                TokenUserAccount oldTokenUserAccount = TokenAccountUtils.getUserAccount(request);
                TokenAccountUtils.removeLoginAccount(oldTokenUserAccount);
            }
            TokenUserAccount tokenUserAccount = createTokenUserAccount(hmUser);
            TokenAccountUtils.cacheLoginAccount(tokenUserAccount);
            TokenAccountUtils.putUserAccount(request, tokenUserAccount);
            return tokenUserAccount;
        }else {
            request.setAttribute("loginErrorMsg", loginResult.getLoginMsg());
            request.setAttribute("loginState", loginResult.getLoginState());
            throw new BusinessException(loginResult.getLoginMsg());
        }
    }


    private TokenUserAccount createTokenUserAccount(HmUser hmUser) {
        String token = genAccessToken(hmUser.getLoginid());
        String loginId = hmUser.getLoginid();
        String mobile = hmUser.getMobile();
        return new TokenUserAccount(token, loginId, mobile);
    }

    //TODO  生成唯一token
    private String genAccessToken(String loginId) {
        String token =  loginId + Calendar.getInstance().getTimeInMillis();
        byte[] hashToken = DigestUtils.sha1(token.getBytes(), EncodesUtils.decodeHex(loginId),
                Constant.HASH_INTERATIONS);
        return EncodesUtils.encodeHex(hashToken);
    }

    /**
     * TODO  参考ShiroDbRealm
     * @Description 用户退出登录时清楚缓存中的token值
     * @param
     * @return
     */
    public TokenUserAccount logout(TokenUserAccount tokenUserAccount) {

        if (tokenUserAccount.getMobile()!=null){
            TokenAccountUtils.removeLoginAccount(tokenUserAccount);
        }else {
            TokenAccountUtils.removeLoginUsrMsg(tokenUserAccount);
        }

        return tokenUserAccount;
    }

}
