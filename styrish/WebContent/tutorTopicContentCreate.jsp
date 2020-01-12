<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
		<h2>Create Content</h2>


		
      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
      <s:param name="subjectId" value="%{subjectId}"></s:param>
      <s:set var="topicList" value="topicList" /> </s:bean>

     
     

		<s:form action="topicCreateContentAction" method="post" enctype="multipart/form-data">
		
		 <select NAME="courseTopicChoice" >
		  <option value="">Select Topic</option>
               <s:iterator value="#topicList" var="topicListMap">
                 <option value="<s:property value="#topicListMap['coursetopic_id']"/>">
                      <s:property value="#topicListMap['topic_name']"/>
                </option>
             </s:iterator>   
       </select>
		    <s:textfield name="contentTitle" label="Document Title" />
		     <s:textarea label="Write Content" name="contents" cols="80" rows="10"/>
			
			<s:submit value="Submit" align="center" />
		</s:form>
	
	
	
	</body>
	   <s:include value="/footer.jsp"></s:include>
</html>