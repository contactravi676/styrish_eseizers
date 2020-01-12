
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <jsp:useBean id="dateValue" class="java.util.Date"/>

 <c:if test="${empty sessionScope.userId }">
   <c:set target="${sessionScope}" property="userId" value="${dateValue.time}"/>
   <c:set target="${sessionScope}" property="userType" value="G"/>
 </c:if>

  
<s:bean name="com.styrish.courses.databean.CoursesDataBean" var="coursesDataBean">
    <s:set var="courseList" value="courseList" />
</s:bean> 
 <c:set var="userSession" value="${sessionScope.userName}"/>
 <c:set var="userId" value="${sessionScope.userId}"/>

<nav class="navbar navbar-inverse"  style="margin:0 0 1px 0;padding:0;width: 100%;display: flex;padding-left: 7%;" >
  <div class="container" style="width: 78%">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="homePage.jsp" style="font-size:20px">Styrish</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar" >
      <ul class="nav navbar-nav" >
		
		 <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="">Courses <span class="caret"></span></a>
			<ul class="dropdown-menu">
		
    		 
				<s:iterator value="#courseList" var="courseListMap">
	                <li><a href="coursePacksAction?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
	            </s:iterator>				
	
          		
			</ul>
        </li>
		
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="">Test Series<span class="caret"></span></a>
			<ul class="dropdown-menu">
				 <s:iterator value="#courseList" var="courseListMap">
                <li><a href="coursePacksAction?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
            </s:iterator>	
			</ul>
        </li>
		
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="">Mock Interviews<span class="caret"></span></a>
			<ul class="dropdown-menu">
				 <s:iterator value="#courseList" var="courseListMap">
                <li><a href="coursePacksAction?courseId=<s:property value="#courseListMap['courseId']"/>"> <s:property value="#courseListMap['course']"/></a></li>
            </s:iterator>	
			</ul>
        </li>       
		<li class=""><a href="">About Us</a></li>
	     <li class=""><a href="">Contact Us</a></li>
	      <c:choose>
			  <c:when test="${empty userSession}">
				     <li class=""><a href="#"  data-toggle="modal" data-target="#myModal" onclick="LoginRegisterModalWindowJS.setRedirectURL('');">Login/Register</a></li>
			  </c:when>
			  <c:otherwise>
				     <li class="dropdown">
			          <a class="dropdown-toggle" data-toggle="dropdown" href="myAccount.jsp">${userSession}<span class="caret"></span></a>
						<ul class="dropdown-menu">
							
			                <li><a href="myAccount.jsp">MyAccount</a></li>
			                 <li><a href="">Course</a></li>
			                  <li><a href="StudentMockTestDisplay.jsp">Mock Test</a></li>
			                   <li><a href="">Mock Videos</a></li>
			                <li><a href="logout">Logout</a></li>
			           	
						</ul>
			        </li>		
			</c:otherwise>
		</c:choose>	
		
      </ul>
      
     
     
    </div>
    
  </div>
   <div id="miniShopCartist" style="background-color: #222;width: 22%;text-align: center;padding-top: 1%;">
         <%@ include file = "miniShopCartContent.jsp"%>
 </div>
 
   
   <%@ include file = "loginModalWindow.jsp"%>
  
</nav>


<script src="js/jquery.form.js"></script>
<script src="js/ApplicationAJAXServices.js"></script>
<script src="js/LoginRegisterModalWindow.js"></script>