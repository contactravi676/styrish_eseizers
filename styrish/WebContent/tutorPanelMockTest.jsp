<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<body>



	<div>
	    <div>
	         <a href="tutorMockTestCreate.jsp">Create Mock Test</a>
		</div> 
		
		</br>
		<div>
		<a href="tutorMockTestPending.jsp">Pending Mock Test</a>
		</div>

		
		</br><div>
			<a href="tutorMockTestResult.jsp">View Mock Test</a>
		</div>
	</div>
</body>
<s:include value="/footer.jsp"></s:include>
</html>
