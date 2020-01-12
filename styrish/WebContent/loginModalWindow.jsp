<!-- Modal HTML -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog modal-login">
		<div class="modal-content" style="height: auto;">
			<form name="loginModalWindowForm" id="loginModalWindowFormId" method="post">
			  <input type="hidden" value="generateOTP" id="actionType">
			  <input type="hidden" value="" id="mobileNumberHidden">
			   <input type="hidden" value="" id="loginRedirectURL">
				<div class="modal-header">				
					<h4 class="modal-title">Login</h4>
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div id="exceptionMessage" style="font-size: 12px;color: red;text-align: center;font-family: Arial, Helvetica, sans-serif"></div>
				<div id="successMessage" style="font-size: 12px;color: green;text-align: center;font-family: Arial, Helvetica, sans-serif"></div>
				<div class="modal-body">				
					<div class="form-group" id="mobileNumberArea">
						<label>Mobile Number</label>
						<a href="#" class="pull-right text-muted" id="registerLinkModalWindow" onclick="LoginRegisterModalWindowJS.enableRegistrationArea();"><small>New User? Register</small></a>
						<input type="text" class="form-control" required="required" name="mobileNumber" id="mobileNumber">
					</div>
					<div class="form-group" id="otpArea" style="display: none;">
						<label>One Time Password</label>
						<input type="text" class="form-control" required="required" name="otp" id="otp">
					</div>
					<div class="form-group" id="loginPasswordField">
						<div class="clearfix">
							<label>Password</label>
							<a href="#" class="pull-right text-muted" id="forgotPasswordLink"><small>Forgot?</small></a>
						</div>
						
						<input type="password" class="form-control" required="required" name="password">
					</div>
					<div class="form-group" id="loginConfirmPasswordField" style="display: none;">
						<div class="clearfix">
							<label>Confirm Password</label>
						</div>
						
						<input type="password" class="form-control" required="required" name="confirmPassword">
					</div>
					<div class="form-group" id="fullNameField" style="display: none;">
						<div class="clearfix">
							<label>Full Name</label>
						</div>
						
						<input type="text" class="form-control" required="required" name="fullName">
					</div>
					
					<div id='loadingmessage' style='display:none'>
               <!--  <img src='img/ajax-loader.png'/> -->
                      <b><font size="2px;">PROCESSING REQUEST...</font></b>
                   </div>
				</div>
				<div class="modal-footer" style="text-align: center;display: none;" id="otploginButtonModalDiv">
					<input type="button" class="btn btn-primary" value="Send OTP" id="otploginButtonModal" 
					onclick="LoginRegisterModalWindowJS.populateMobileHiddenField(document.getElementById('mobileNumber').value,document.getElementById('actionType').value);ApplicationAJAXServicesJS.OTPLoginServiceAjax(document.getElementById('mobileNumberHidden').value,document.getElementById('actionType').value,document.getElementById('otp').value,document.getElementById('loginRedirectURL').value);">
				</div>
				<div class="modal-footer" style="text-align: center;display: none;" id="verifyOTPtoRegisterButtonModalDiv">
					<input type="button" class="btn btn-primary" value="Verify OTP" id="verifyOTPtoRegisterButtonModal" onclick="ApplicationAJAXServicesJS.AccountRegistrationServiceAjax(document.getElementById('loginRedirectURL').value);"/>
				</div>
				<div class="modal-footer" style="text-align: center;" id="loginButtonModalDiv">
					<input type="button" class="btn btn-primary" value="Login" id="loginButtonModal" 
					onclick="ApplicationAJAXServicesJS.AccountLoginServiceAjax(document.getElementById('loginRedirectURL').value);">
				</div>
				<div class="modal-footer" style="text-align: center;display: none;" id="registrationButtonModalDiv">
					<input type="button" class="btn btn-primary" value="Register" id="registrationButtonModal" 
					onclick="ApplicationAJAXServicesJS.AccountRegistrationServiceAjax(document.getElementById('loginRedirectURL').value);">
				</div>
			</form>
			<div style="text-align: center;">
			      <a href="#" onclick="LoginRegisterModalWindowJS.enableOTPLoginArea();" id="loginWithOtpLink">Login With OTP</a>
			      <a href="#" style="display: none;" id="loginWithAccountLink" onclick="LoginRegisterModalWindowJS.enableAccountLoginArea();">Login With Account</a>
			</div>
			
		</div>
	</div>
</div>