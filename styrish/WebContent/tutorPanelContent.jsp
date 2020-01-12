<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<body>


 <s:form action="createTopicAction">
 <s:bean name="com.styrish.courses.databean.CourseSubjectDataBean" var="courseSubjectDataBean">
<s:set var="courseSubjectList" value="courseSubjectList" /> </s:bean> 

<select NAME="subjectId" >
		  <option value="">Select Subject</option>
          <s:iterator value="#courseSubjectList" var="courseSubjectListMap">
                 <option value="<s:property value="#courseSubjectListMap['coursesubject_id']"/>">
                      <s:property value="#courseSubjectListMap['subjectname']"/>
                </option>
             </s:iterator>   
       </select></br>
       
      
      
     
   <div>
	     <div>
		 <s:submit  value="Create Topics" />
		</div>
		</br>
	     <div>
			 <s:submit action="createContentAction" value="Create Content" />
		</div>
		</br>
		<div>
			 <s:submit action="uploadContentAction" value="Upload Content" />
		</div>
		</br>
		<div>
			  <s:submit action="createVideoAction" value="Upload Video" />
		</div>
		</br>
		<div>
		<s:submit action="createExerciseAction" value="Create Exercise" />
		</div>
		</br>
		
		</div>
  </s:form>
</body>
<s:include value="/footer.jsp"></s:include>
</html>