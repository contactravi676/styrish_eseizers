<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>

<%-- <s:set var="courseId" value="13" /> --%>
<s:bean name="com.styrish.courses.databean.CourseSubjectDataBean" var="courseSubjectDataBean">
<s:param name="courseId" value="#courseId"></s:param>
<s:set var="courseSubjectList" value="courseSubjectList" /> </s:bean> 

  <s:set var="subjectIds" value="subjectId" />
	<s:form action="topicCreateAction" method="post" >
		<%-- <s:hidden name="courseId"  value="13" /> --%>
		<s:hidden name="subjectId"  value="%{subjectIds}" />
	        <s:textfield name="topicName" label="Topic Name" />
			 <s:textfield name="topicDescription" label="Topic Description" />
			<s:submit value="Create" align="center" />
		</s:form>

          
</body>

</body>
<s:include value="/footer.jsp"></s:include>
</html>