<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">

<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
</head>
  <s:include value="/header.jsp"></s:include>
<body>

<s:set var="mockTestId" value="%{mockTestId}" />

 <s:set var="mockTestIds" value="#mockTestListMap['mocktest_id']" />
 <s:bean name="com.styrish.mocktest.bean.MockTestQuestionsDataBean" var="mockTestQuestionsDataBean">
<s:param name="mockTestId" value="mockTestId"></s:param>
<s:set var="mockQuestionsList" value="mockQuestionsList" /> </s:bean>

</br>
 <table border=1  align="center">
        <tr><td>Question</td><td>Option A</td><td>Option B</td><td>Option C</td><td>Option D</td><td>Correct Option</td><td>Hint</td></tr>
        <s:iterator value="#mockQuestionsList" var="mockQuestionsListMap">
       
 
        <tr>
            <td><s:property value="#mockQuestionsListMap['mockquestion']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionA']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionB']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionC']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockOptionD']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockCorrectOption']"/></td>
            <td><s:property value="#mockQuestionsListMap['mockHint']"/></td>
       </tr>
        
        </s:iterator>
        </table>
      </br>

</body>
 <s:include value="/footer.jsp"></s:include>
</html>