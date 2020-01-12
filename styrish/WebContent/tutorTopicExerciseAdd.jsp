<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>

 
      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
      <s:param name="subjectId" value="%{subjectId}"></s:param>
      <s:set var="topicList" value="topicList" /> </s:bean>  
        
  
<s:form action="topicCreateExerciseAction">

  
  

		 <select NAME="topicId" >
		  <option value="">Select Topic</option>
               <s:iterator value="#topicList" var="topicListMap">
                 <option value="<s:property value="#topicListMap['coursetopic_id']"/>">
                      <s:property value="#topicListMap['topic_name']"/>
                </option>
             </s:iterator>   
       </select>
 
 <s:textarea label="Question" name="question" cols="80" rows="10"/>
           <s:textfield name="optionA" label="Option A" />
           <s:textfield name="optionB" label="Option B" />
           <s:textfield name="optionC" label="Option C" />
           <s:textfield name="optionD" label="Option D" />
  <s:textfield name="questionHint" label="Hint" />
               <s:select label="correct option"
name="correctOption"
headerKey="0"
headerValue="correct option"
list="#{'1':'optionA', '2':'optionB', '3':'optionC', '4':'optionD'}"/>    
          
        <s:submit />
        </s:form> 
  
  
        <s:set var="topicIds" value="%{topicId}" />
        
        <s:bean name="com.styrish.courses.topics.databean.TopicExerciseDataBean" var="topicExerciseDataBean">
        <s:param name="topicId" value="%{topicIds}"></s:param>
        <s:set var="exerciseList" value="exerciseList" /> </s:bean> 
    <table border=1>
        <tr><td>Question</td><td>Option A</td><td>Option B</td><td>Option C</td><td>Option D</td><td>Correct Option</td><td>Hint</td></tr>    
     <s:iterator value="#exerciseList" var="exerciseMap">
     <tr>
      <td> <s:property value="#exerciseMap['question']"/></td>
        <td><s:property value="#exerciseMap['optionA']"/></td>
      <td>  <s:property value="#exerciseMap['optionB']"/></td>
       <td> <s:property value="#exerciseMap['optionC']"/></td>
       <td> <s:property value="#exerciseMap['optionD']"/></td>
       <td> <s:property value="#exerciseMap['correctOption']"/></td>
       <td> <s:property value="#exerciseMap['questionHint']"/></td>
       <td> <s:property value="#exerciseMap['question_id']"/></td>
       <td><a href="topicEditExerciseAction?questionId=<s:property value="#exerciseMap['question_id']"/>"><font color='red'>EDIT</font></a></td>
       <td><a href="topicDeleteExerciseAction?questionId=<s:property value="#exerciseMap['question_id']"/>"><font color='red'>DELETE</font></a></td>
     </tr>  
     </s:iterator>
   </table>  
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>