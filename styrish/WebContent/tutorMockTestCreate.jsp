<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">

<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>

      <c:set var="courseIdSession" value="${sessionScope.courseIdSession}"/>
       <c:set var="userIdSession" value="${sessionScope.userId}"/>

      
      <s:bean name="com.styrish.courses.databean.SubCoursesDataBean" var="subCoursesDataBean">
     <s:set var="subCourseList" value="subCourseList" /> </s:bean>
        
  
         <s:form action="tutorMockTestCreateAction">

       <select NAME="subCourseId" >
		  <option value="">Select SubCourse</option>
              <s:iterator value="#subCourseList" var="subCourseMap">
                 <option value="<s:property value="#subCourseMap['subcoursesId']"/>">
                      <s:property value="#subCourseMap['subcourse']"/>
                </option>
             </s:iterator>   
       </select>
           <s:textfield name="mocktestName" label="Mock Test Name" />
           <s:textarea label="Exam GuideLines" name="examGuidlines" cols="80" rows="10"/>
           <s:textfield name="totalTime" label="Total Time" />
           <s:textfield name="totalQuestions" label="Total Questions" />
           <s:hidden name="mocktestStatus"  value="P" />
            </br>
        <s:submit />
        </s:form>  
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>