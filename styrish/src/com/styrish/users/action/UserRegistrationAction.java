package com.styrish.users.action;


import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

public class UserRegistrationAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -2613425890762568273L;
    private String firstName;
	private String password;
	private String mobile;
	private String email;
	private String confirmPassword;
	private String role;
	private SessionMap<String,Object> sessionMap;  
	
	UserRegistrationDaoImpl userRegistrationDaoImpl = new UserRegistrationDaoImpl();
	
	 
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
   public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap<String, Object>)map;  
	}  

	

	public String Users() {
		return "Users";
	}

	public String execute() {
	
	userRegistrationDaoImpl.save(this);
		Map userMap=userRegistrationDaoImpl.fetchUserMap(email)	;	
		sessionMap.put("userId", userMap.get("user_id"));
		sessionMap.put("userName", userMap.get("userName"));
		sessionMap.put("user_type", userMap.get("user_type"));
		
		return SUCCESS;
		
	}
	
	public void validate(){
		
		
		if(!(getPassword().equals(getConfirmPassword()))){
			addFieldError("confirmPassword", "Confirm password must be equal to password");
		}
		
		String exitUser=userRegistrationDaoImpl.getUserId(email);
		
				if(exitUser!=null){
					addFieldError("email", "EmailId is already exist");		
				}
		
		
	}
}
