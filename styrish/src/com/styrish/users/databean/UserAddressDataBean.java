package com.styrish.users.databean;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.styrish.users.dao.UserRegistrationDaoImpl;

public class UserAddressDataBean {
	
	private Map<String,String> addressMap=null;
	private String userId =null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Map<String, String> getAddressMap() {
		
		UserRegistrationDaoImpl userRegistrationDaoImpl =new UserRegistrationDaoImpl();
		 HttpSession session=ServletActionContext.getRequest().getSession(false);
		 if(session.getAttribute("userId")!=null) {
				userId=(String) session.getAttribute("userId");	
			}
		
		addressMap=userRegistrationDaoImpl.getUserAddressMap(userId);
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	
	
	

}
