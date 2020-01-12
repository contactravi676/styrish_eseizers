package com.styrish.users.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

public class AjaxUserLoginAction extends ActionSupport 
                                 implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> sessionMap;
	private String userName;
	private String password;
	private String mobileNumber;
	private boolean loginSuccess;
	private UserRegistrationDaoImpl userRegistrationDaoImpl;
	private Long usersId;

	
	@Override
	public String execute() throws Exception {
	
		initiateSession();
		return "success";
	}
	
	public void initiateSession() {
		
		Map<String,String> userMap=getUserRegistrationDaoImpl().fetchUserMap(getMobileNumber())	;	
		sessionMap.put("userId", userMap.get("user_id"));
		sessionMap.put("userName", userMap.get("userName"));
		sessionMap.put("user_type", userMap.get("user_type"));
		sessionMap.put("userType", "R");
		setUsersId(Long.valueOf((String)sessionMap.get("userId")));
		
	}
	
	

	@Override
	public void validate() {
		
		boolean aunthatication= false;
		
		if (getMobileNumber().length() == 0) {

			addFieldError("userName", "UserName.required");
		}else if (getPassword().length() == 0) {

			addFieldError("password", getText("password.required"));
		} else {
		    aunthatication=getUserRegistrationDaoImpl().validateLogin(getMobileNumber(),getPassword());
        }

		if (aunthatication == false) {
            addFieldError("password", "Invalid UserName or Password");
		}else {
			setLoginSuccess(true);
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public boolean isLoginSuccess() {
		return loginSuccess;
	}

	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}

	public UserRegistrationDaoImpl getUserRegistrationDaoImpl() {
		
		if (userRegistrationDaoImpl == null) {
			userRegistrationDaoImpl = new UserRegistrationDaoImpl();
		}
		
		return userRegistrationDaoImpl;
	}

	public void setUserRegistrationDaoImpl(UserRegistrationDaoImpl userRegistrationDaoImpl) {
		this.userRegistrationDaoImpl = userRegistrationDaoImpl;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}
	
	

}
