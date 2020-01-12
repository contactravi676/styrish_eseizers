<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>

  
     
       <s:set var="questionId" value="questionId" />
      <s:bean name="com.styrish.courses.topics.databean.TopicExerciseDataBean" var="topicExerciseDataBean">
      <s:param name="questionId" value="%{questionId}"></s:param>
      <s:set var="questionMap" value="questionMap" /> </s:bean>  
        

<s:form action="topicUpdateExerciseAction">

  

 
	<c:set var="question" value="${questionMap['question']}"/>	
	
	
 
 <s:textarea label="Question" name="question" cols="80" rows="10" value="%{questionMap.question}"/>
           <s:textfield name="optionA" label="Option A" value="%{questionMap.optionA}"/>
           <s:textfield name="optionB" label="Option B" value="%{questionMap.optionB}" />
           <s:textfield name="optionC" label="Option C" value="%{questionMap.optionC}"/>
           <s:textfield name="optionD" label="Option D" value="%{questionMap.optionD}"/>
  <s:textfield name="questionHint" label="Hint" value="%{questionMap.questionHint}"/>
               <s:select label="correct option"
name="correctOption"
headerKey="0"
headerValue="correct option"
list="#{'1':'optionA', '2':'optionB', '3':'optionC', '4':'optionD'}"/>  

<s:hidden name="questionId"  value="%{questionId}" />  
          
        <s:submit />
        </s:form> 
 <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>