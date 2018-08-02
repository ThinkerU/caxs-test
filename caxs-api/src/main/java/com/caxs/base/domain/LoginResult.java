/**
 * 软件著作权：长安新生（深圳）金融投资有限公司
 * 
 * 系统名称：马达贷
 * 
 */
package com.caxs.base.domain;


import java.io.Serializable;


/**
 * 用户行为记录日志
 * 
 * @author 郑翔
 * 
 */
public class LoginResult implements Serializable {


	private static final long serialVersionUID = 1004130350270562607L;

	private String loginState;

	private boolean loginUnlock = false;

	private String loginId;

	private String ID;

	private String loginMsg;

	/**
	 * 用户信息
	 */
	private HmUser hmUser;

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public boolean isLoginUnlock() {
		return loginUnlock;
	}

	public void setLoginUnlock(boolean loginUnlock) {
		this.loginUnlock = loginUnlock;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public HmUser getHmUser() {
		return hmUser;
	}

	public void setHmUser(HmUser hmUser) {
		this.hmUser = hmUser;
	}

	public String getLoginMsg() {
		return loginMsg;
	}

	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}
}