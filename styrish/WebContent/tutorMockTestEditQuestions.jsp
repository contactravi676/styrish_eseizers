<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>
<s:set var="questionId" value="%{questionId}" />

 
        
<s:bean name="com.styrish.mocktest.bean.MockTestQuestionsDataBean" var="mockTestQuestionsDataBean">
<s:param name="questionId" value="questionId"></s:param>
<s:set var="mockQuestionsMap" value="mockQuestionsMap" /> </s:bean>        
  
<s:form action="tutorMockTestUpdateQuestionsAction">


<s:set var="question" value="#mockQuestionsMap['mockquestion']" />
<s:set var="optionA" value="#mockQuestionsMap['mockOptionA']" />
<s:set var="optionB" value="#mockQuestionsMap['mockOptionB']" />
<s:set var="optionC" value="#mockQuestionsMap['mockOptionC']" />
<s:set var="optionD" value="#mockQuestionsMap['mockOptionD']" />
<s:set var="Hint" value="#mockQuestionsMap['mockHint']" />

 <s:hidden name="questionId"  value="%{questionId}" />
 
 <s:textarea label="Question" name="question" cols="80" rows="10" value="%{question}" />
           <s:textfield name="optionA" label="Option A" value="%{optionA}" />
           <s:textfield name="optionB" label="Option B" value="%{optionB}"/>
           <s:textfield name="optionC" label="Option C" value="%{optionC}"/>
           <s:textfield name="optionD" label="Option D" value="%{optionD}" />
  <s:textfield name="questionHint" label="Hint"  value="%{Hint}"/>
               <s:select label="correct option" 
name="correctOption"
headerKey="0"
headerValue="correct option"
list="#{'1':'optionA', '2':'optionB', '3':'optionC', '4':'optionD'}"/>    
          
        <s:submit />
        </s:form>  
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>