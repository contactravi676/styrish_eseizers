
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<%@ include file = "Environment.jsp"%>
<%@ include file = "mainMenu.jsp"%>
 <body>

     

      
      <s:bean name="com.styrish.courses.databean.SubCoursesDataBean" var="subCoursesDataBean">
     <s:set var="subCourseList" value="subCourseList" /> </s:bean>
        
  
        <s:form action="tutorEBookUploadAction" method="post" enctype="multipart/form-data">   

       <select NAME="subcourseId" >
		  <option value="">Select SubCourse</option>
              <s:iterator value="#subCourseList" var="subCourseMap">
                 <option value="<s:property value="#subCourseMap['subcoursesId']"/>">
                      <s:property value="#subCourseMap['subcourse']"/>
                </option>
             </s:iterator>   
       </select>
          
        <s:textfield name="documentTitle" label="Video Title" />
        <s:textfield name="documentDescription" label="Video Description" />
		<s:file name="document" label="Select the file to upload" />
       <s:submit value="upload" align="center" />
		</s:form>
	<c:if test="${not empty document}">
    	
	
	<h3>File Uploaded successfully</h3>
 File Name :
 <s:property value="documentFileName"></s:property>
 <br /> Content type:
 <s:property value="documentContentType"></s:property>
 <br /> User file :
 <s:property value="document"></s:property>


	</c:if>
			
	
        
         <s:include value="/footer.jsp"></s:include>
           
    </body>
</html>