package com.styrish.footer.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.footer.dao.FooterLinksDAOImpl;


public class ContactUsAction extends ActionSupport {
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String mobile;
	private String message;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String execute() throws Exception {

		FooterLinksDAOImpl footerLinksDAOImpl = new FooterLinksDAOImpl();
		footerLinksDAOImpl.createContactUs(this);

		return SUCCESS;
	}


}
