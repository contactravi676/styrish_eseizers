package com.styrish.users.action;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.users.dao.UserRegistrationDaoImpl;

public class UserOTPLoginAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String CLASSNAME = "UserOTPLoginAction";
	protected static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("StyrishApplicationProperties");
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	protected String mobileNumber;
	protected String actionType;
	protected String otp;
	protected boolean otpCreated;
	protected boolean otpValidated;
	protected boolean otpExpired;
	protected String exceptionMessage;
	protected String exceptionMessageKey;
	protected long MAX_DURATION = MILLISECONDS.convert(2, MINUTES);
	protected Map<String, Object> sessionMap;
	protected Long usersId;

	@Override
	public String execute() throws Exception {
		final String METHODNAME = "execute";
		String actionType = getActionType();
		if (StringUtils.isNotEmpty(actionType) && "GENERATEOTP".equalsIgnoreCase(actionType)) {
			LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "generating OTP for Mobile {0}", getMobileNumber());
			CreateSendOTPTaskCmd createSendOTPTaskCmd = new CreateSendOTPTaskCmd();
			createSendOTPTaskCmd.setMobileNumber(getMobileNumber());
			createSendOTPTaskCmd.setActionType("GENERATEOTP");
			createSendOTPTaskCmd.execute();
			if (createSendOTPTaskCmd.getOtp() != null) {
				setOtpCreated(true);

			}else {
				setExceptionMessage(createSendOTPTaskCmd.getExceptionMessage());
				setExceptionMessageKey(createSendOTPTaskCmd.getExceptionMessageKey());
			}
		} else if (StringUtils.isNotEmpty(actionType) && "VERIFYOTP".equalsIgnoreCase(actionType)) {
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
				setExceptionMessageKey("ERR_OTP_EXPITRED");
			} else if (createSendOTPTaskCmd.isOtpValidated()) {
				setOtpValidated(true);
				doLogin();
			}
		}

		return "success";
	}

	protected void doLogin() {

		UserRegistrationDaoImpl userRegistrationDaoImpl = new UserRegistrationDaoImpl();
		Map<String, String> userMap = userRegistrationDaoImpl.fetchUserMap(getMobileNumber());
		sessionMap.put("userId", userMap.get("user_id"));
		sessionMap.put("userName", userMap.get("userName"));
		sessionMap.put("user_type", userMap.get("user_type"));
		sessionMap.put("userType", "R");
		setUsersId(Long.valueOf((String) sessionMap.get("userId")));
	}

	public boolean isOtpCreated() {
		return otpCreated;
	}

	public void setOtpCreated(boolean otpCreated) {
		this.otpCreated = otpCreated;
	}

	public boolean isOtpValidated() {
		return otpValidated;
	}

	public void setOtpValidated(boolean otpValidated) {
		this.otpValidated = otpValidated;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
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

	public boolean isOtpExpired() {
		return otpExpired;
	}

	public void setOtpExpired(boolean otpExpired) {
		this.otpExpired = otpExpired;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

}
