package com.styrish.users.action;
import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;


public class UserLoginAction extends ActionSupport implements SessionAware{  

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	private SessionMap<String, Object> sessionMap;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	UserRegistrationDaoImpl userRegistrationDaoImpl = new UserRegistrationDaoImpl();

	@Override
	public void validate() {


		boolean aunthatication=userRegistrationDaoImpl.validateLogin(username,password);

		if (getUsername().length() == 0) {

			addFieldError("userName", "UserName.required");
		}else if (getPassword().length() == 0) {

			addFieldError("password", getText("password.required"));
		}else if (aunthatication == false) {

			addFieldError("password", "Invalid UserName or Password");
		}
	}

	public String execute() {
		Map<String,String> userMap=userRegistrationDaoImpl.fetchUserMap(username)	;	
		sessionMap.put("userId", userMap.get("user_id"));
		sessionMap.put("userName", userMap.get("userName"));
		sessionMap.put("user_type", userMap.get("user_type"));
		sessionMap.put("userType", "R");
	

		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = (SessionMap<String, Object>) sessionMap;
	}





}