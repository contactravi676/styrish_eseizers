package com.styrish.users.action;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.styrish.users.dao.OTPDAOImpl;
import com.styrish.users.databean.OTPDataBean;

public class CreateSendOTPTaskCmd {

	protected static final String CLASSNAME = "CreateSendOTPTaskCmd";
	protected static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("StyrishApplicationProperties");
	protected static final Logger LOGGER = Logger.getLogger(CLASSNAME);
	protected String actionType;
	protected String mobileNumber;
	protected String otp;
	protected boolean otpValidated;
	protected boolean otpExpired;
	protected String exceptionMessage;
	protected String exceptionMessageKey;
	protected long MAX_DURATION = MILLISECONDS.convert(2, MINUTES);

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public boolean isOtpValidated() {
		return otpValidated;
	}

	public void setOtpValidated(boolean otpValidated) {
		this.otpValidated = otpValidated;
	}

	public boolean isOtpExpired() {
		return otpExpired;
	}

	public void setOtpExpired(boolean otpExpired) {
		this.otpExpired = otpExpired;
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

	public long getMAX_DURATION() {
		return MAX_DURATION;
	}

	public void setMAX_DURATION(long mAX_DURATION) {
		MAX_DURATION = mAX_DURATION;
	}

	public void execute() throws Exception {

		final String METHODNAME = "execute";
		if (!StringUtils.isEmpty(getMobileNumber()) && !StringUtils.isEmpty(getActionType())) {
			if (getActionType().equalsIgnoreCase("GENERATEOTP")) {
				LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "generating OTP for Mobile {0}", getMobileNumber());
				long diff = 0;
				OTPDataBean otpDataBean = getValidOTP();
				Date now = new Date();
				Date otpCreatedDate = otpDataBean.getCreatedDate();
				if (otpCreatedDate != null) {
					diff = now.getTime() - otpCreatedDate.getTime();
				}
				if (otpDataBean.getOtp() != null && (diff < MAX_DURATION)) {

					String message = "Please wait for 2 minutes to request a new OTP. ";
					setExceptionMessage(message);
					setExceptionMessageKey("ERR_WAIT_TO_REGENRATE_OTP");
				} else {
					generateOTP(6);
					setOTPInDB();
					// sendOTPToUser();
				}

			} else if (getActionType().equalsIgnoreCase("VERIFYOTP")) {
				verifyOTP();
			}

		}
	}

	protected void verifyOTP() throws Exception {

		final String METHODNAME = "verifyOTP";
		String otp = getOtp();
		String mobileNumber = getMobileNumber();
		if (StringUtils.isNotEmpty(otp) && StringUtils.isNotEmpty(mobileNumber)) {

			OTPDataBean otpDataBean = getValidOTP();
			Date now = new Date();
			Date otpCreatedDate = otpDataBean.getCreatedDate();
			if (now.getTime() - otpCreatedDate.getTime() >= MAX_DURATION) {
				this.setOtpExpired(true);
				invalidateOTP(otpDataBean);
			} else {
				if (otp.equals(otpDataBean.getOtp())) {
					LOGGER.logp(Level.INFO, CLASSNAME, METHODNAME, "OTP Validated");
					this.setOtpValidated(true);
					invalidateOTP(otpDataBean);
				} else {
					this.setOtpValidated(false);
				}
			}

		}

	}

	protected OTPDataBean getValidOTP() {

		OTPDataBean otpDataBean = new OTPDataBean();
		otpDataBean.setMobileNumber(mobileNumber);
		OTPDAOImpl otpdaoImpl = new OTPDAOImpl();
		otpdaoImpl.fetchOTP(otpDataBean);

		return otpDataBean;
	}

	protected void invalidateOTP(OTPDataBean otpDataBean) {

		OTPDAOImpl otpdaoImpl = new OTPDAOImpl();
		otpdaoImpl.invalidateOTP(otpDataBean);

		return;
	}

	protected void generateOTP(int size) {
		final String METHODNAME = "generateOTP";
		StringBuilder generatedToken = new StringBuilder();
		try {
			SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
			for (int i = 0; i < size; i++) {
				generatedToken.append(number.nextInt(9));
			}

			this.setOtp(generatedToken.toString());
			LOGGER.logp(Level.INFO, CLASSNAME, METHODNAME, "Generated OTP Is ,{0}", getOtp());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	protected void setOTPInDB() {
		final String METHODNAME = "setOTPInSession";
		OTPDataBean otpDataBean = new OTPDataBean();
		OTPDAOImpl loginOTPDAOImpl = new OTPDAOImpl();
		Date now = new Date();
		otpDataBean.setMobileNumber(getMobileNumber());
		otpDataBean.setOtp(getOtp());
		otpDataBean.setStatus("P");
		otpDataBean.setCreatedDate(now);
		loginOTPDAOImpl.createOTP(otpDataBean);
		LOGGER.logp(Level.FINE, CLASSNAME, METHODNAME, "OTP Set In DB");

	}

	protected void sendOTPToUser() {
		final String METHODNAME = "sendOTPToUser";
		JSONObject urlParameters = new JSONObject();
		try {
			urlParameters.put("apikey", RESOURCE_BUNDLE.getString("API_KEY"));
			urlParameters.put("secret", RESOURCE_BUNDLE.getString("SECRET_KEY"));
			urlParameters.put("usetype", RESOURCE_BUNDLE.getString("USE_TYPE"));
			urlParameters.put("phone", getMobileNumber());
			MessageFormat messageFormat = new MessageFormat(RESOURCE_BUNDLE.getString("MESSAGE"));
			String messageString = messageFormat.format(new Object[] { getOtp() });
			urlParameters.put("message", URLEncoder.encode(messageString, "UTF-8"));
			urlParameters.put("senderid", RESOURCE_BUNDLE.getString("SENDERID"));

			URL url = new URL(
					new StringBuilder(RESOURCE_BUNDLE.getString("URL")).append("/api/v1/sendCampaign").toString());

			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
			wr.write(urlParameters.toString().getBytes());

			BufferedReader bufferedReader = null;
			if (httpConnection.getResponseCode() == 200) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
			}
			StringBuilder content = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line).append("\n");
			}
			bufferedReader.close();
			LOGGER.logp(Level.INFO, CLASSNAME, METHODNAME, "Response from SMS gateway" + content.toString());

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			// no need to return real OTP in response due to safety purpose
			// this.setOtp("******");
		}

	}

}
