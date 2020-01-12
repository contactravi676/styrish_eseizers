<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">

<%-- <%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 --%></head>
 <body>

<s:bean name="com.styrish.student.databean.StudentMockTestDataBean" var="studentMockTestDataBean">
<s:param name="courseId" value="'13'"></s:param>
<s:set var="mockTestList" value="mockTestList" /> </s:bean>
 

 
      
       <table border=1 align="center">
       <tr><td>Subcourse </td><td>Mock test Name </td><td>Total Questions</td><td>Total Time</td><td>Start/View Report</td></tr>
   <s:iterator value="#mockTestList" var="mockTestMap">
     <tr>
      
      <td><s:property value="#mockTestMap['subcourse']"/></td>
      <td><s:property value="#mockTestMap['mocktest_name']"/></td>
      <td><s:property value="#mockTestMap['noOfQuestions']"/></td>
     <td><s:property value="#mockTestMap['testTime']"/></td>
     <td><a href ="studentMockTestPaperAction?mockTestId=<s:property value="#mockTestMap['mocktest_id']"/>">Start Test</a></td>
    
     </tr>
   </s:iterator>     
       </table>
      
       
       
       
          <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>