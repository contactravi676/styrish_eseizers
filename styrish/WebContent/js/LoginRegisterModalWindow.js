/**
 * 
 */

LoginRegisterModalWindowJS = {

	enableOTPLoginArea : function() {
		
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$("#actionType").val("generateOTP");
		$("#mobileNumberHidden").val("");
		$("#mobileNumberArea").css("display", "block");
		$("#otpArea").css("display", "none");
		$("#loginWithAccountLink").css("display", "block");
		$("#loginWithOtpLink").css("display", "none");
		$("#loginPasswordField").css("display", "none");
		$("#otploginButtonModalDiv").css("display", "block");
		$("#loginButtonModalDiv").css("display", "none");
		$("#registrationButtonModalDiv").css("display", "none");
		$("#loginModalWindowFormId").attr('action', 'ajax/AjaxOTPLoginAction');
		$("#otploginButtonModal").val("Send OTP");
		$("#loginConfirmPasswordField").css("display", "none");
		$("#fullNameField").css("display", "none");
		$("#verifyOTPtoRegisterButtonModalDiv").css("display", "none");

	},
	
	enableOTPVerifyAreaForRegister : function() {
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$('#loadingmessage').hide();
		$("#mobileNumberArea").css("display", "none");
		$("#otpArea").css("display", "block");
		$('.btn').show();
		$("#actionType").val("verifyOTP");
		$("#loginConfirmPasswordField").css("display", "none");
		$("#loginPasswordField").css("display", "none");
		$("#fullNameField").css("display", "none");
		$("#registrationButtonModalDiv").css("display", "none");
		$("#verifyOTPtoRegisterButtonModalDiv").css("display", "block");
	},

	enableAccountLoginArea : function() {
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$("#loginWithAccountLink").css("display", "none");
		$("#loginWithOtpLink").css("display", "block");
		$("#loginPasswordField").css("display", "block");
		//$("#loginButtonModal").val("Login");
		$("#otploginButtonModalDiv").css("display", "none");
		$("#loginButtonModalDiv").css("display", "block");
		$("#registrationButtonModalDiv").css("display", "none");
		$("#loginModalWindowFormId").attr('action', 'AccountLoginAction');
		$("#loginConfirmPasswordField").css("display", "none");
		$("#fullNameField").css("display", "none");
		$("#verifyOTPtoRegisterButtonModalDiv").css("display", "none");
	},
	
	enableRegistrationArea :function() {
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$("#loginConfirmPasswordField").css("display", "block");
		$("#loginPasswordField").css("display", "block");
		$("#fullNameField").css("display", "block");
		$("#registrationButtonModalDiv").css("display", "block");
		$("#otploginButtonModalDiv").css("display", "none");
		$("#loginButtonModalDiv").css("display", "none");
		$("#forgotPasswordLink").css("display", "none");
		$("#verifyOTPtoRegisterButtonModalDiv").css("display", "none");
		
	},
	
	populateMobileHiddenField : function (mobileNumber,actionType) {
		if(actionType == "generateOTP") {
			 $("#mobileNumberHidden").val(mobileNumber);
		 }
		
	},
	
	setRedirectURL : function (redirectURL) {
		 $("#loginRedirectURL").val(redirectURL);
	}

}