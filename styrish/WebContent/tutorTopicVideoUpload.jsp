<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
<body>
	
		<h2>Upload Content Video</h2>


		
      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
      <s:param name="subjectId" value="%{subjectId}"></s:param>
      <s:set var="topicList" value="topicList" /> </s:bean>

     
     

		<s:form action="topicUploadVideoAction" method="post" enctype="multipart/form-data">
		
		 <select NAME="courseTopicChoice" >
		  <option value="">Select Topic</option>
               <s:iterator value="#topicList" var="topicListMap">
                 <option value="<s:property value="#topicListMap['coursetopic_id']"/>">
                      <s:property value="#topicListMap['topic_name']"/>
                </option>
             </s:iterator>   
       </select>
		    <s:textfield name="documentTitle" label="Document Title" />
		     <s:textfield name="documentDescription" label="Description" />
		<s:file name="document" label="Select the file to upload" />
       <s:submit value="upload" align="center" />
		</s:form>
	
	
 <h3>File Uploaded successfully</h3>
  <s:property value="documentFileName"></s:property>
 <br /> Content type:
 <s:property value="documentContentType"></s:property>
 <br /> User file :
 <s:property value="document"></s:property>
 
 <s:url id="fileDownload" namespace="/" action="topicUploadContentAction" ></s:url>

<h4>Download file:: - <a href="<s:property value="documentPath"/>"><s:property value="documentFileName"/></a>
</h4>

			
	</body>
	   <s:include value="/footer.jsp"></s:include>
</html>