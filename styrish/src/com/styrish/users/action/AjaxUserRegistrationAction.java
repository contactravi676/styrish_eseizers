package com.styrish.users.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

public class AjaxUserRegistrationAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> sessionMap;
	private String mobileNumber;
	private String otp;
	private boolean otpValidated;
	private boolean otpCreated;
	private String password;
	private String confirmPassword;
	private String fullName;
	private boolean isRegistered;
	private List<String> validationMessages;
	private String validationMessage;
	private UserRegistrationDaoImpl userRegistrationDaoImpl;
	private Long usersId;
	protected String exceptionMessage;
	protected String exceptionMessageKey;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	@Override
	public String execute() throws Exception {

		boolean userCreated = false;
		if (StringUtils.isEmpty(getOtp())) {
			Map<String, String> userParamMap = new HashMap<String, String>();
			userParamMap.put("mobileNumber", mobileNumber);
			userParamMap.put("password", password);
			userParamMap.put("confirmPassword", confirmPassword);
			userParamMap.put("fullName", fullName);
			if (getValidationMessages().isEmpty()) {
				userCreated = getUserRegistrationDaoImpl().createInactiveUser(userParamMap);
			}

			if (userCreated || (getValidationMessages().size() == 1
					&& getValidationMessages().contains("ERR_UNACTIVATED_ACCOUNT_EXISTS"))) {
				// send otp to mobile for verifying mobile
				CreateSendOTPTaskCmd createSendOTPTaskCmd = new CreateSendOTPTaskCmd();
				createSendOTPTaskCmd.setMobileNumber(getMobileNumber());
				createSendOTPTaskCmd.setActionType("GENERATEOTP");
				createSendOTPTaskCmd.execute();
				if (createSendOTPTaskCmd.getOtp() != null) {
					setOtpCreated(true);

				} else {
					setExceptionMessage(createSendOTPTaskCmd.getExceptionMessage());
					setExceptionMessageKey(createSendOTPTaskCmd.getExceptionMessageKey());
				}
			}
		} else if (!StringUtils.isEmpty(this.getOtp())) {
			// verify OTP
			CreateSendOTPTaskCmd createSendOTPTaskCmd = new CreateSendOTPTaskCmd();
			createSendOTPTaskCmd.setMobileNumber(getMobileNumber());
			createSendOTPTaskCmd.setOtp(getOtp());
			createSendOTPTaskCmd.setActionType("VERIFYOTP");
			createSendOTPTaskCmd.execute();
			if (!createSendOTPTaskCmd.isOtpValidated() && !createSendOTPTaskCmd.isOtpExpired()) {
				String message = " Incorrect OTP, please try again !";
				setExceptionMessage(message);
				setExceptionMessageKey("ERR_INCORRECT_OTP");
			} else if (createSendOTPTaskCmd.isOtpExpired()) {
				String message = " OTP has been expired , please request a new one !";
				setExceptionMessage(message);

			} else if (createSendOTPTaskCmd.isOtpValidated()) {
				setOtpValidated(true);
				initiateSession();
				getUserRegistrationDaoImpl().activateUser(Long.valueOf((String) sessionMap.get("userId")));
			}
		}

		/*
		 * initiateSession(); if (sessionMap != null && !sessionMap.isEmpty()) {
		 * setRegistered(true); }
		 */
		return "success";
	}

	public void initiateSession() {

		Map<String, String> userMap = getUserRegistrationDaoImpl().fetchUserMap(getMobileNumber());
		sessionMap.put("userId", userMap.get("user_id"));
		sessionMap.put("userName", userMap.get("userName"));
		sessionMap.put("user_type", userMap.get("user_type"));
		sessionMap.put("userType", "R");
		setUsersId(Long.valueOf((String) sessionMap.get("userId")));

	}

	public UserRegistrationDaoImpl getUserRegistrationDaoImpl() {

		if (userRegistrationDaoImpl == null) {
			userRegistrationDaoImpl = new UserRegistrationDaoImpl();
		}

		return userRegistrationDaoImpl;
	}

	protected boolean checkIfAccountExists() throws Exception {
		boolean accountExists = false;

		Map<String, String> accountMap = getUserRegistrationDaoImpl().checkIfAccountExists(getMobileNumber());
		if (accountMap != null && !accountMap.isEmpty()) {
			String accountStatus = accountMap.get("user_status");

			if (!StringUtils.isEmpty(accountStatus) && accountStatus.contentEquals("0")) {
				getValidationMessages().add("ERR_UNACTIVATED_ACCOUNT_EXISTS");

			}
		}
		return accountExists;

	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionMessageKey() {
		return exceptionMessageKey;
	}

	public void setExceptionMessageKey(String exceptionMessageKey) {
		this.exceptionMessageKey = exceptionMessageKey;
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

	@Override
	public void validate() {
		List<String> validationMessages = new ArrayList<String>();
		setValidationMessages(validationMessages);
		try {
			if (password != null && !password.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
				if (!password.equals(confirmPassword)) {
					getValidationMessages().add("ERR_PASSWORD_NOT_MATCH");

				}
			}

			checkIfAccountExists();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(List<String> validationMessages) {
		this.validationMessages = validationMessages;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public void setUserRegistrationDaoImpl(UserRegistrationDaoImpl userRegistrationDaoImpl) {
		this.userRegistrationDaoImpl = userRegistrationDaoImpl;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isOtpValidated() {
		return otpValidated;
	}

	public void setOtpValidated(boolean otpValidated) {
		this.otpValidated = otpValidated;
	}

	public boolean isOtpCreated() {
		return otpCreated;
	}

	public void setOtpCreated(boolean otpCreated) {
		this.otpCreated = otpCreated;
	}

}
