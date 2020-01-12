/**
 * 
 */

ApplicationAJAXServicesJS = {

	AccountLoginServiceAjax : function(redirectURL) {
		
		$('.btn').hide();
		$('#loadingmessage').show();
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$("#loginModalWindowFormId").ajaxSubmit(
				{
					url : "ajax/AjaxAccountLoginAction",
					type : "post",
					dataType : "json",
					responseType : "json",
					success : function(data) {
						if (data.loginSuccess == true) {
							if (redirectURL != null && redirectURL != "") {
								if (data.usersId != null) {
									redirectURL = redirectURL + "&usersId="
											+ data.usersId;
								}
							}
							window.location.href = redirectURL;
						} else {
							$("#exceptionMessage").html("Login Failed");
						}
						$('.btn').show();
						$('#loadingmessage').hide();

					},
					error : function(xhr, errorType, exception) {

					}

				});

	},
	
	
	AccountTeachersLoginServiceAjax : function(redirectURL) {
		
		
		
		$("#teachersLoginFormId").ajaxSubmit(
				{
					url : "../ajax/AjaxTeachersAccountLoginAction",
					type : "post",
					dataType : "json",
					responseType : "json",
					success : function(data) {
						
						if (data.loginSuccess == true) {
							if (redirectURL != null && redirectURL != "") {
							    redirectURL = redirectURL;
							}
							window.location.href = redirectURL;
						} else {
							$("#exceptionMessage").html("Login Failed");
						}
						
					},
					error : function(xhr, errorType, exception) {
						alert("some error"+exception);

					}

				});

	},
	
	
	AccountRegistrationServiceAjax : function(redirectURL) {
		$('.btn').hide();
		$('#loadingmessage').show();
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$("#loginModalWindowFormId").ajaxSubmit(
				{
					url : "ajax/AjaxAccountRegisterAction",
					type : "post",
					dataType : "json",
					responseType : "json",
					success : function(data) {
						var validationMessages = data.validationMessages;
						if (data.exceptionMessage != null) {
							$("#exceptionMessage").html(
									data.exceptionMessage);
							$('.btn').show();
							$('#loadingmessage').hide();
						}else if(validationMessages.includes("ERR_UNACTIVATED_ACCOUNT_EXISTS")) 
						{
							
							LoginRegisterModalWindowJS.enableOTPVerifyAreaForRegister();
							$("#successMessage").html(
									"Your Account already exists but not activated , To activate it " +
									",please verify the mobile number with " +
									"One Time Password sent on your registered mobile.");
							
							
						} else if (data.otpCreated == true) {
							LoginRegisterModalWindowJS.enableOTPVerifyAreaForRegister();
							$("#successMessage").html(
									"Your Account has been created, To activate it " +
									",please verify the mobile number with " +
									"One Time Password sent on your registered mobile.");
							
						}
						if(data.otpValidated == true){
							if (redirectURL != null && redirectURL != "") {
								if (data.usersId != null) {
									redirectURL = redirectURL + "&usersId="
											+ data.usersId;
									
									
								}
							}
							window.location.href = redirectURL;
							$('.btn').show();
							$('#loadingmessage').hide();
							$('#myModal').modal('toggle');
						} else {
							/*
							 * if (data.exceptionMessage != null) {
							 * $("#exceptionMessage").html(
							 * data.exceptionMessage); }
							 */
						}

					},
					error : function(data) {
                         
					}

				});

	},

	OTPLoginServiceAjax : function(mobileNumber, actionType, otp, redirectURL) {

		$('.btn').hide();
		$('#loadingmessage').show();
		$("#exceptionMessage").html("");
		$("#successMessage").html("");
		$
				.ajax({
					type : "POST",
					url : "ajax/AjaxOTPLoginAction",
					data : (otp != null) ? "mobileNumber=" + mobileNumber
							+ "&actionType=" + actionType + "&otp=" + otp
							: "mobileNumber=" + mobileNumber + "&actionType="
									+ actionType,
					dataType : 'json',
					responseType : 'json',
					success : function(data) {
						var ht = data.msg;
						var otp = data.otp;
						var otpValidated = data.otpValidated;
						
						if(data.exceptionMessage != null) {
							$("#exceptionMessage").html(
									data.exceptionMessage);
							if (data.exceptionMessageKey == "ERR_OTP_EXPITRED"
									|| data.exceptionMessageKey == "ERR_WAIT_TO_REGENRATE_OTP") {
								
								$('#loadingmessage').hide();
								$('.btn').show();
							}

						}else if (otp != null) {
							$('#loadingmessage').hide();
							$("#mobileNumberArea").css("display", "none");
							$("#otpArea").css("display", "block");
							$('.btn').show();
							$("#actionType").val("verifyOTP");
							$("#otploginButtonModal").val("Verify OTP");
						}
						if (otpValidated != null && otpValidated == true) {
							if (redirectURL != null && redirectURL != "") {
								if (data.usersId != null) {
									redirectURL = redirectURL + "&usersId="
											+ data.usersId;
								}
							}

							window.location.href = redirectURL;
						} 

					},
					error : function(xhr, errorType, exception) {

					}
				});

	},

	AddItem2ShopCartAjax : function(productId, userId) {

		var productId = productId;
		var userId = userId;
		$('.btn').hide();
		$('#loadingmessage1').show();

		$.ajax({
			type : "POST",
			url : "ajax/AjaxOrderItemAddAction",
			data : "productId=" + productId + "&userId=" + userId,
			dataType : 'json',
			success : function(data) {
				var ht = data.msg;
				//$("#resp").html(ht);
				$('#loadingmessage1').hide();
				$('#miniShopCartist').load('miniShopCartContent.jsp');
				$("#myModal1").modal('show');
				$('.btn').show();
				$('#orderIdHidden').val(data.ordersId);
			},
			error : function(data) {
				alert("Some error occured.");
			}
		});

	},

	RemoveItemFromCartAjax : function(orderItemsID, ordersID,usersId) {

		var productId = productId;
		var userId = userId;
		$('#loadingmessageItem_' + orderItemsID).show();
		$('#itemRemoveLinkId_' + orderItemsID).hide();

		$.ajax({
			type : "POST",
			url : "ajax/AjaxOrderItemDeleteAction",
			data : {
				"orderItemsId" : orderItemsID,
				"ordersId" : ordersID,
				"usersId"  : usersId
			},
			dataType : 'json',
			success : function(data) {

				/*
				 * $('#loadingmessageItem_' + orderItemsID).hide();
				 * $('#itemRemoveLinkId_' + orderItemsID).show();
				 */
				$('#orderSummaryDivId').load(
						'orderAmountDisplay.jsp?ordersId=' + data.ordersId);
				$('#miniShopCartist').load('miniShopCartContent.jsp');
				$('#shopCartDivId').load(
						'orderItemDetail.jsp?ordersId=' + data.ordersId);
				// $("#myModalItemRemoved").modal('show');
			},
			error : function(data) {
				alert("Some error occured.");
			}
		});

	},
	
	
	
     loadSubjects : function (courseId,teacherPanel) {
    	 $('#uploadContentTopicListID').children().remove().end();
    	 $('#uploadContentSubjectListID').children().remove().end();
    	 courseId=courseId.split("_")[0];
    	 var subjectList;
    	 
    	 $.ajax({
 			type : "POST",
 			url : "../ajax/AjaxGetSubjectsByCourse",
 			data : {
 				
 				"courseId" : courseId
 			},
 			dataType : 'json',
 			respnseType : 'json',
 			success : function(data) {
               subjectList = data.subjectList;
               if(subjectList) {
            	   $('#uploadContentSubjectListID').append('<option value="" selected >select</option>');
	               for (var i = 0; i <= subjectList.length; i++) {
	            	   
	            	   var subject = subjectList[i];
	            	  // alert(subject);
	            	   var hashtable = {};
	            	   $.each(subject, function (key, value) {
	            		  // hashtable[key]=value;
	            		   if(teacherPanel==true){
	            			   $('#uploadContentSubjectListID').append('<option value="' + key +'_' + value + '">' + value + '</option>');
	            		   }
	            		   
	            		 });
	            	  
	               }
               }
 			},
 			error : function(data) {
 				alert("Some error occured.");
 			}
 		});
    	 
     },
     
     
     loadTopics : function(subjectID,userID) {
    	$('#uploadContentTopicListID').children().remove().end();
    	subjectID = subjectID.split("_")[0];
    	//alert(subjectID);
        var topicList;
    	 $.ajax({
 			type : "POST",
 			url : "../ajax/AjaxGetTopicBySubjectAndUser",
 			data : {
 				
 				"subjectId" : subjectID,
 				"usersId"   : userID
 			},
 			dataType : 'json',
 			respnseType : 'json',
 			success : function(data) {
 				topicList = data.topicList;
               if(topicList) {
	               for (var i = 0; i <= topicList.length; i++) {
	            	   
	            	   var topic = topicList[i];
	            	  // alert(subject);
	            	   var hashtable = {};
	            	   $.each(topic, function (key, value) {
	            		   //hashtable[key]=value;
	            		      
	            			   $('#uploadContentTopicListID').append('<option value="' + key + '_' + value + '">' + value + '</option>');
	            		 
	            	   });
	            	  
	            	  //console.log(hashtable);
	               }
	               
	               $('#uploadContentTopicListID').append('<option value="-1">New Topic</option>');
               }
 			},
 			error : function(data) {
 				alert("Some error occured.");
 			}
 		});
    	 
    	 
     },
     
     CreateContent : function() {
    	 $("#uploadContentMessage").html("");
    	 $("#fileUploadFormID").ajaxSubmit(
 				{
 					url : "../ajax/AjaxUploadContentAction",
 					type : "post",
 					dataType : "json",
 					responseType : "json",
 					success : function(data) {
 						
 						var fullPath;
 						
 						var actionErrors = data.actionErrors;
 						var errorMessage = '';
 						if (actionErrors != null && actionErrors.length > 0) {
	 						for (i=0; i<actionErrors.length; i++) {
	 							if (actionErrors[i] != null && actionErrors[i]=='ERR_BLANK_TOPIC') {
	 								errorMessage = errorMessage+"Topic Can not be blank !!<br/>";
	 							}
	 							if (actionErrors[i] != null && actionErrors[i]=='ERR_BLANK_SUBJECT') {
	 								errorMessage = errorMessage+"Subject Can not be blank !!<br/>";
	 							}
	 							if (actionErrors[i] != null && actionErrors[i]=='ERR_BLANK_COURSE') {
	 								errorMessage = errorMessage+"Course Can not be blank !!<br/>";
	 							}
	 							if (actionErrors[i] != null && actionErrors[i]=='ERR_BLANK_CONTENT_TYPE') {
	 								errorMessage = errorMessage+"Content Type Can not be blank !!<br/>";
	 							}
	 							if (actionErrors[i] != null && actionErrors[i]=='ERR_BLANK_FILE') {
	 								errorMessage = errorMessage+"Please select file to upload !!<br/>";
	 							}
	 						}
	 						$("#uploadContentMessage").css("color","red");
	 						$("#uploadContentMessage").html(errorMessage);
 						} else {
 							if (data.directoryPath != null && data.directoryPath != '' && data.fileName != null && data.fileName != '') {
 								fullPath = data.directoryPath+data.fileName;
 								fullPath = fullPath.trim().replace(/\s/g, '%20');
 	 						}
 							var messageKey = data.message;
	 						var messageValue;
	 						if (messageKey != null && messageKey=='TOPIC_CREATED') {
	 							messageValue = "New Topic Created  !!";
	 						} else if (messageKey != null && messageKey=='VERSON_UPDATED') {
	 							messageValue = "New Version of existing topic Created  !!";
	 						}
	 						$("#uploadContentMessage").css("color","green");
	 						$("#uploadContentMessage").html(messageValue);
	 						$('#contentDisplayDivId').load('ContentDisplay.jsp?contentType='+data.contentType+'&filePath='+fullPath);
	 						$("#uploadFileButtonDiv").css("display","none");
	 						$("#approveContentButtonDiv").css("display","block");
	 						$("#topicIdHidden").val(data.topicID);
	 						$("#topicVersionHidden").val(data.version);
	 						$("#contentTypeHidden").val(data.contentType);
	 						
 						}
 					},
 					error : function(data) {
 						alert("some error"+data.actionErrors);

 					}

 				});
    	 
     },
     
     ApproveContent : function(contentId,version,contentTypeHidden) {
    	//alert(contentId+'--'+version+'--'+contentTypeHidden);
    	 $.ajax({
  			type : "POST",
  			url : "../ajax/AjaxApproveContentAction",
  			data : {
  				"topicID" : contentId,
  				"version"   : version,
  				"contentTypeHidden" : contentTypeHidden
  			},
  			dataType : 'json',
  			respnseType : 'json',
  			success : function(data) {
  				$("#uploadContentMessage").css("color","green");
				$("#uploadContentMessage").html("Topic Approved !!");
  				$("#uploadFileButtonDiv").css("display","block");
				$("#approveContentButtonDiv").css("display","none");
  				
  				
  			},
  			error : function(data) {
  				alert("Some error occured.");
  			}
  		});
     	 
    	 
    	 
    	 
     }
}