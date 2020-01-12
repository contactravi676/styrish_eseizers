<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
  <title>Home</title>
  <!-- Start WOWSlider.com HEAD section -->
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css"><link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/explore.css"><script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script><script src="js/tooltip.js"></script>
<script src="js/slider.js"></script>
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
</head>
<body>
<html lang="en">
<head>
  <title>Home</title>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/tooltip.js"></script>
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet"> 
  
  </head>

<%@ include file = "mainMenu.jsp"%>

 <c:set var="userSession" value="${sessionScope.userName}"/>
 <c:set var="roleSession" value="${sessionScope.user_type}"/>
  <c:set var="userIdSession" value="${sessionScope.userId}"/>
   
<table border=1>
<tr><td>User Name</td><td>Role</td></tr>
<tr><td>${userSession}</td><td>${roleSession}</td><td>${userIdSession}</td></tr>
</table>
</br>
</br>
<s:bean name="com.styrish.users.databean.UserAddressDataBean" var="userAddressDataBean">
<s:param name="userId" value="%{userIdSession}"></s:param>
<s:set var="addressMaps" value="addressMap" /> </s:bean>

<c:choose>
  <c:when test="${roleSession == 'Teacher'}">
  <s:bean name="com.styrish.courses.databean.CoursesDataBean" var="coursesDataBean">
    <s:set var="courseList" value="courseList" />
   </s:bean>  
    <s:form action="tutorPanelAction" method="post" enctype="multipart/form-data">
		
		 <select NAME="courseId" >
		  <option value="">Select Course</option>
              <s:iterator value="#courseList" var="courseListMap">
                 <option value="<s:property value="#courseListMap['courseId']"/>">
                      <s:property value="#courseListMap['course']"/>
                </option>
             </s:iterator>   
       </select>
		    
			
			<s:submit value="Tutor Panel" align="center" />
		</s:form>
  <a href="tutorPanelMain.jsp"><font color="red">Tutor Panel</font></a>
  
 </c:when>
  <c:otherwise>
        Welcome, <s:property value="firstName"></s:property> This is your Account.
</c:otherwise>
</c:choose>

  <s:include value="/footer.jsp"></s:include>