package com.styrish.users.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

public class UserAddressAction extends ActionSupport{
	
	
	
	private static final long serialVersionUID = 1L;
	private String fullName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String userId;
	private String userPhoto;
	private String idProof;
	private String email;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String execute() {
		UserRegistrationDaoImpl userRegistrationDaoImpl = new UserRegistrationDaoImpl();
		Map<String,String> userMap=userRegistrationDaoImpl.fetchUserMap(email)	;
		setUserId(userMap.get("user_id"));
		userRegistrationDaoImpl.inserAddress(this);

		return SUCCESS;
	}

}
