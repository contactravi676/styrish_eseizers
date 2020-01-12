<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<c:set var="courseId" value="${sessionScope.courseIdSession}"/>



<body>


	<div>
	     <div>
			<a href="tutorPanelContent.jsp">Contents</a>
		</div>
		</br>
	     <div>
			<a href="tutorPanelMockTest.jsp">Mock Test</a>
		</div>
		</br>
		<div>
			<a href="tutorMockVideoMain.jsp">Mock Videos</a>
		</div>
		</br>
		<div>
			<a href="tutorEbookMain.jsp">E-Book</a>
		</div>
		</br>
		<div>
			<a href="tutorTopicDisplay.jsp">View Course Content</a>
		</div>
	</div>
</body>
<s:include value="/footer.jsp"></s:include>
</html>
