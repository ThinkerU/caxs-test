package com.caxs.base.domain;

import java.io.Serializable;

public class TokenUserAccount implements Serializable {

    private String token;

    private String loginId;

    private String mobile;

    public TokenUserAccount(String token,String loginId,String mobile){
        this.token = token;
        this.loginId = loginId;
        this.loginId = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
