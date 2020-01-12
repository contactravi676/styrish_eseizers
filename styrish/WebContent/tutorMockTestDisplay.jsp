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

<s:set var="mockTestIds" value="mockTestId" />
<s:bean name="com.styrish.mocktest.bean.MockTestDataBean" var="mockTestDataBean">
<s:param name="mockTestId" value="mockTestIds"></s:param>
<s:set var="mockTestMap" value="mockTestMap" /> </s:bean>
 
::::<s:property value="mockTestId"/>
 <s:bean name="com.styrish.mocktest.bean.MockTestQuestionsDataBean" var="mockTestQuestionsDataBean">
<s:param name="mockTestId" value="mockTestIds"></s:param>
<s:set var="mockQuestionsList" value="mockQuestionsList" /> </s:bean>
      
       <table border=1 align="center">
       <tr><td>Mock test Name </td><td>${mockTestMap.mocktest_name}</td></tr>
       <tr><td>Guidelines</td><td>${mockTestMap.testInstructions}</td></tr>
       <tr><td>Total Questions</td><td>${mockTestMap.noOfQuestions}</td></tr>
       <tr><td>Total Time</td><td>${mockTestMap.testTime}</td></tr>
       </table>
      
       
       
        <table border=1>
        <tr><td>Question</td><td>Option A</td><td>Option B</td><td>Option C</td><td>Option D</td><td>Correct Option</td><td>Hint</td></tr>
        <s:iterator value="#mockQuestionsList" var="mockQuestionsListMap">
       
 
        <tr><td><s:property value="#mockQuestionsListMap['mockquestion']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionA']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionB']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionC']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionD']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockCorrectOption']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockHint']"/></td>
        <td><a href="tutorMockTestEditQuestionsAction?questionId=<s:property value="#mockQuestionsListMap['mocktestquestions_id']" />"><font color='red'>EDIT</font></a></td>
        <td><a href="tutorMockTestDeleteQuestionsAction?questionId=<s:property value="#mockQuestionsListMap['mocktestquestions_id']" />"><font color='red'>DELETE</font></a></td>
        </tr>
        
        </s:iterator>
        </table>
   
    </br>
     <div><a href="tutorMockTestQuestionsAction?mockTestId=<s:property value="mockTestId"/>"><font color='red'>Add Question</font></a></div>   
     </br>
        <div><a href="tutorMockTestFinalizeAction?mockTestId=<s:property value="mockTestId"/>"><font color='red'>Finalize Mock Test</font></a></div>
          <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>