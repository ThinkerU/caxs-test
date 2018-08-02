package com.caxs.base.service;

import com.caxs.base.constatnt.Constant;
import com.caxs.base.daos.HmUserMapper;
import com.caxs.base.domain.HmUser;
import com.caxs.base.domain.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService implements ILoginService{

    @Autowired
    private HmUserMapper hmUserMapper;

    @Override
    public LoginResult getLoginResult(String loginId, String passWord) {
        LoginResult loginResult = new LoginResult();
        loginResult.setLoginUnlock(false);
        HmUser hmUser = hmUserMapper.slectByLoginId(loginId);
        if(null == hmUser){
            loginResult.setLoginState(Constant.LOGIN_STATUS_NOACCOUNT);
            loginResult.setLoginMsg("您还不是该系统已注册用户");
        }else{
            if(!passWord.equals(hmUser.getPassword())){
                loginResult.setLoginState(Constant.LOGIN_STATUS_WRONGPWD);
                loginResult.setLoginMsg("密码错误");
            }else{
                loginResult.setLoginState(Constant.LOGIN_STATUS_RIGHT);
                loginResult.setHmUser(hmUser);
            }
        }
        return loginResult;
    }
}
