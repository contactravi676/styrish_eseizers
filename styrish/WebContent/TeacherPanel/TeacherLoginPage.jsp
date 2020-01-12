<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
  <title>Teacher-Login Area</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/ApplicationAJAXServices.js"></script>
</head>

<body>

      <div class="mainContainerDiv">
            <div class="loginContainerDiv" id="tutorloginDiv">
		           <div style="text-align: center;color: black;margin-top: 5%;font-family: Arial, Helvetica, sans-serif;">
		                <h3>Teacher Login Area</h3>
		           </div>
			   <form method="POST" id="teachersLoginFormId">
			       <div style="padding: 20px;display: flex;">
			           <label style="width: 30%;color: black;font-weight: normal;margin-top: 2px;">Mobile No</label>
			           <input class="form-control" name="mobileNumber" type="text" value="">
			       </div>
			       <div style="padding: 20px;display: flex;">
			           <label style="width: 30%;color: black;font-weight: normal;margin-top: 2px;">Password</label>
			           <input class="form-control" name="password" type="password" value="">
			       </div>
			       
			   </form>
			    <div style="padding: 20px;display: flex;">
			           <button class="glass" style="margin-left: 5%;" onclick="Javascript:ApplicationAJAXServicesJS.AccountTeachersLoginServiceAjax('TeacherLoginSuccess.jsp');">Login</button>
			    </div>
			</div>
			<div style="background-color: #fff;padding-top: 10%;color: black;">&#9400; All Rights Reserverd with - Styrish Inc.</div>
			
			
       </div>


</body>


</html>