<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<body>

  <c:set var="courseId" value="${sessionScope.courseId}" scope="session"/>
 <c:set var="subjectIds" value="%{subjectId}" />

      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
      <s:param name="subjectId" value="%{subjectId}"></s:param>
      <s:set var="topicList" value="topicList" /> </s:bean>
 
 <c:set var="subjectIdSession" value="${sessionScope.subjectId}"/>


<table border=1 align="center">
<tr>

<td>Topic Name</td>
<td>Description</td>
</tr>

 <s:iterator value="#topicList" var="topicListMap">
            <tr>
            <td> <s:property value="#topicListMap['topic_name']"/></td>
            <td> <s:property value="#topicListMap['shortdescription']"/></td>
             <td><a href="topicEditAction?topicId=<s:property value="#topicListMap['coursetopic_id']"/>">Edit</a></td>
              <td><a href="topicDeleteAction?topicId=<s:property value="#topicListMap['coursetopic_id']"/>">Delete</a></td>
            </tr>     
                     
                
             </s:iterator> 
   </table> 
   </br></br>
   <div><a href="topicAddAction?subjectId=${sessionScope.subjectId}"><font color="red">Add Topic</font></a></div>
   </br></br>   
    <div><a href="tutorPanelContent.jsp"><font color="red">Back</font></a></div>        
</body>

  <s:include value="/footer.jsp"></s:include>
</html>