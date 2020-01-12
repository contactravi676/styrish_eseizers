<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">

<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>

    
        
  
<s:form action="tutorMockTestAddQuestionsAction">

  <s:hidden name="mockTestId"  value="%{mockTestId}" />
  
 
 
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
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>