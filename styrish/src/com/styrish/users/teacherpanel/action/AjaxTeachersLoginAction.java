package com.styrish.users.teacherpanel.action;

import com.styrish.users.action.AjaxUserLoginAction;

public class AjaxTeachersLoginAction extends AjaxUserLoginAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3130054210589838235L;

	private String userName;
	private String password;
	private String mobileNumber;
	private boolean loginSuccess;

	private Long usersId;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}
	
	

}
