package com.styrish.users.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserLogoutAction extends ActionSupport implements SessionAware{

	
	private static final long serialVersionUID = 1L;
	SessionMap<String,String> sessionmap;  


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}  

  


public String logout(){  
	Map sessionMap = ActionContext.getContext().getSession();

	if(sessionMap!=null){
	sessionMap.remove("userName");
	sessionMap.remove("user_type");
	sessionMap.remove("userId");
	sessionMap.remove("userType");
	}
	
    return "LOGOUT";  
}





}
