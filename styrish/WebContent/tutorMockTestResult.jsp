<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">

<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
</head>
 <body>
 
 
       <s:set var="mockStatus" value="'P_S'" />
<s:bean name="com.styrish.mocktest.bean.MockTestDataBean" var="mockTestDataBean">
<s:param name="mockTestStatus" value="%{mockStatus}"></s:param>
<s:set var="mockTestLists" value="mockTestList" /> </s:bean>


<table border=1>
       <tr><td>Mock test Name</td><td>Total Questions</td><td>Total Time</td><td>Status</td></tr>
 <s:iterator value="#mockTestLists" var="mockTestListMap">
 
  
 
       
      <tr> <td><a href="tutorMockTestDetailsAction?mockTestId=<s:property value="#mockTestListMap['mocktest_id']"/>"><s:property value="#mockTestListMap['mocktest_name']"/></a></td>
      <td><s:property value="#mockTestListMap['noOfQuestions']"/></td>
       <td><s:property value="#mockTestListMap['testTime']"/></td>
       <td><s:property value="#mockTestListMap['mocktest_status']"/></td></tr>
  
 </s:iterator>
 
  </table>
</body>
 <s:include value="/footer.jsp"></s:include>
</html>