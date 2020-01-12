package com.styrish.users.teacherpanel.action;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class TeachersBaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -543418229560738292L;
	protected String URL;
	protected String actionType;
	@Override
	public String execute() throws Exception {
		
		if(!StringUtils.isEmpty(actionType) && actionType.equalsIgnoreCase("uploadContent")) {
			this.setURL("UploadContent.jsp");
		}
		if(!StringUtils.isEmpty(actionType) && actionType.equalsIgnoreCase("ViewAccountDetails")) {
			this.setURL("ViewAccountDetails.jsp");
		}
		if(!StringUtils.isEmpty(actionType) && actionType.equalsIgnoreCase("changePassword")) {
			this.setURL("ChangePassword.jsp");
		}
		
		return super.execute();
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	

}
