<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <title>Home</title>
   <link href="css/style.css" rel="stylesheet"> 
 </head> 
<body>
<s:bean name="com.styrish.courses.databean.CoursesDataBean" var="coursesDataBean">
<s:set var="courseList" value="courseList" /> </s:bean> 

 <c:set var="userSession" value="${sessionScope.userName}"/>
 
         


<div class="body-wrap">
  <div class="container">
    <nav class="navbar navbar-inverse " role="navigation" style="background-color: #e3f2fd;" >
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="homePage.jsp">Styrish</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav">
           
             <c:choose>
  <c:when test="${empty userSession}">
  
          <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Course<b class="caret"></b></a>
              <ul class="dropdown-menu">
              
              <s:iterator value="#courseList" var="courseListMap">
                
              <li><a href="coursePacksAction?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
               
             </s:iterator>
             </ul>
            </li>
  </c:when>
  <c:otherwise>
          <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Course<b class="caret"></b></a>
              <ul class="dropdown-menu">
              
              <s:iterator value="#courseList" var="courseListMap">
                
              <li><a href="courses?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
               
             </s:iterator>
             </ul>
            </li>
  </c:otherwise>
</c:choose>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Course<b class="caret"></b></a>
              <ul class="dropdown-menu">
              
              <s:iterator value="#courseList" var="courseListMap">
                
              <li><a href="courses?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
               
             </s:iterator>
             </ul>
            </li>
            
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mock Test<b class="caret"></b></a>
               <ul class="dropdown-menu">
              
              <s:iterator value="#courseList" var="courseListMap">
                
              <li><a href="courses?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
               
             </s:iterator>
             </ul>
            </li>
            
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Mock Interview<b class="caret"></b></a>
               <ul class="dropdown-menu">
              
              <s:iterator value="#courseList" var="courseListMap">
                
              <li><a href="courses?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
               
             </s:iterator>
             </ul>
            </li>
        
       
      
  <c:choose>
  <c:when test="${empty userSession}">
  
         <li><a href="userLogin.jsp">Register/Login</a></li>
  </c:when>
  <c:otherwise>
         <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">${userSession}<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="myAccount.jsp">MyAccount</a></li>
                <li><a href="logout">Logout</a></li>
                
             </ul>
            </li>
  </c:otherwise>
</c:choose>
          
           
          </ul>

         
        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container-fluid -->
    </nav>
  </div>
</div>
</body>
</html>