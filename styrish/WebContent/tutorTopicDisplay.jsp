<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
	<head><title>Styrish</title></head>
	<body>
 	
      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
     <s:set var="tutorTopicList" value="tutorTopicList" /> </s:bean>

<body>


<table border=1 align="center">
<tr>
<td>Details</td>
<td>Topic Name</td>
<td>Description</td>
</tr>

 <s:iterator value="#tutorTopicList" var="topicListMap">
            <tr>
            <td><a href="topicDisplayDetailsAction?topicId=<s:property value="#topicListMap['coursetopic_id']"/>">Details</a></td>
            <td> <s:property value="#topicListMap['topic_name']"/></td>
            <td> <s:property value="#topicListMap['shortdescription']"/></td>
            </tr>     
                     
                
             </s:iterator> 
   </table>            
</body>

  <s:include value="/footer.jsp"></s:include>
</html>