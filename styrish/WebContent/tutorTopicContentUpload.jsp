<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
	<head><title>Styrish</title></head>
	<body>
	
	 
		<h2>Upload Content</h2>


	
      
      <s:bean name="com.styrish.courses.topics.databean.CoursesTopicDataBean" var="coursesTopicDataBean">
      <s:param name="subjectId" value="%{subjectId}"></s:param>
      <s:set var="topicList" value="topicList" /> </s:bean>

     
     

		<s:form action="topicUploadContentAction" method="post" enctype="multipart/form-data">
		
		 <select NAME="courseTopicChoice" >
		  <option value="">Select Topic</option>
               <s:iterator value="#topicList" var="topicListMap">
                 <option value="<s:property value="#topicListMap['coursetopic_id']"/>">
                      <s:property value="#topicListMap['topic_name']"/>
                </option>
             </s:iterator>   
       </select>
		    <s:textfield name="documentTitle" label="Document Title" />
			<s:file name="userImage" label="Create Content" />
			<s:submit value="Upload" align="center" />
		</s:form>
	
	
	<h2>Content Loaded Successfully</h2>
		
    	Content Type:<s:property value="userImageContentType" /><br/>
		File Name:<s:property value="documentPath" /><br/>
<s:url id="fileDownload" namespace="/" action="topicUploadContentAction" ></s:url>

<h4>Download file:: - <a href="<s:property value="documentPath"/>"><s:property value="documentPath"/></a>
</h4>

			
	</body>
	   <s:include value="/footer.jsp"></s:include>
</html>