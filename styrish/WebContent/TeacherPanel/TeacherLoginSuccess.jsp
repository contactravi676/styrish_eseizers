<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Panel - My Account</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/ApplicationAJAXServices.js"></script>
</head>
<body>


<div class="mainContainerDiv">
            <div class="myAccountContainerDiv" id="tutorloginDiv">
		           <div style="text-align: center;color: black;margin-top: 5%;font-family: Arial, Helvetica, sans-serif;">
		                <h3>My Account</h3>
		           </div>
		           
		           <div style="padding: 20px;display: flex;">
			           <label class="myAccountContent">My Account Details</label>
			            <button class="myAccountButtons"  onclick="window.location.href='TeacherBaseAction?actionType=viewAccountDetails'">Go</button>
			       </div>
			       <div style="padding: 20px;display: flex;">
			           <label class="myAccountContent">Change Password</label>
			            <button class="myAccountButtons"  onclick="window.location.href='TeacherBaseAction?actionType=changePassword'">Go</button>
			       </div>
			        <div style="padding: 20px;display: flex;">
			           <label class="myAccountContent">Upload Content</label>
			            <button class="myAccountButtons" onclick="window.location.href='TeacherBaseAction?actionType=uploadContent'">Go</button>
			       </div>
			  
			</div>
			<div style="background-color: #fff;padding-top: 10%;color: black;">&#9400; All Rights Reserverd with - Styrish Inc.</div>
			
			
       </div>


</body>
</html>