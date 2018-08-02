package com.caxs.base.service;

import com.caxs.base.domain.LoginResult;

public interface ILoginService {
    LoginResult getLoginResult(String loginId,String passWord);
}
