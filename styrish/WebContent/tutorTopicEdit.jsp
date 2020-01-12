<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<body>




  <s:set var="subjectIds" value="subjectId" />
	<s:form action="topicUpdateAction" method="post" >
		    
		    <s:hidden name="topicId"  value="%{topicId}" />
	        <s:textfield name="topicName" label="Topic Name" value="%{topicName}"/>
			 <s:textfield name="topicDescription" label="Topic Description" value="%{topicDescription}"/>
			<s:submit value="Edit" align="center" />
		</s:form>

          
</body>

</body>
<s:include value="/footer.jsp"></s:include>
</html>