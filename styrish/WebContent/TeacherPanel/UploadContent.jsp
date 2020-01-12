<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Panel - My Account</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/jquery.form.js"></script>
<script src="../js/ApplicationAJAXServices.js"></script>
</head>
<body>

<s:bean name="com.styrish.courses.databean.CoursesDataBean" var="coursesDataBean">
    <s:set var="courseList" value="courseList" />
</s:bean> 

<div class="mainContainerDiv">
  <div style="width: 100%;background-color: #fff;height: 30%;color: black;text-align: center;">
	     <h3>Upload Content</h3>
	</div>
	<div style="width: 60%;text-align: center;margin-left: 20%;color: green;" id="uploadContentMessage"></div>
  <s:form action="" name="fileUploadForm" id="fileUploadFormID" enctype="multipart/form-data" method="POST">
   <div class="uploadContentMainFormSection">
     
	   <div class="uploadContentMainFormItemsSection">
	     <label class="myAccountContent">Course Name</label>
	     <select class="form-control" onchange="ApplicationAJAXServicesJS.loadSubjects(this.value,true);" name="courseName">
	     <option value="" selected>select</option>
	     <s:iterator value="#courseList" var="courseListMap">
	        <option value="<s:property value="#courseListMap['courseId']"/>_<s:property value="#courseListMap['course']"/>"><s:property value="#courseListMap['course']"/></option>
	     </s:iterator>
	        
	     </select>
	   </div>
	   <div class="uploadContentMainFormItemsSection">
	     <label class="myAccountContent">Subject Name</label>
	     
	     <select class="form-control" id="uploadContentSubjectListID" name="subjectName" onchange="ApplicationAJAXServicesJS.loadTopics(this.value,<c:out value="${sessionScope.userId }"/>);">
	       <option value="" selected>select</option>
	     </select>
	   </div>
	   <div class="uploadContentMainFormItemsSection">
	     <label class="myAccountContent">Topic Name</label>
	     <select class="form-control" id="uploadContentTopicListID" name="topicName">
	         <option value="" selected>select</option>
	     </select>
	     <input type="hidden" value="" name="topicNameText" id="newTopicID" class="form-control">
	   </div>
	   <div class="uploadContentMainFormItemsSection">
	     <label class="myAccountContent">Content Type</label>
	     <select class="form-control" name="contentType">
	         <option value="" selected>Select</option>
	         <option value="Notes">Notes</option>
	         <option value="Video">Video</option>
	         <option value="Exercise">Exercise</option>
	     </select>
	   </div>
	   
	</div>
	<div class="uploadContentMainFormUploadFileSection">
	   <div class="uploadContentMainFormUploadFileNameTextSection">
	        <input type="text" class="form-control" id="uploadFilePath" name="fileName"/>
	        
	   </div>
	   <div class="uploadContentMainFormUploadButtonSection">
	         <!--  <button class="myAccountButtons"  onclick="createFileUploadElement();">Select File</button>  -->
	        <input type="file" onchange="copyFilePath(this, 'uploadFilePath');" name="fileUpload"/>
	         <%-- <s:file name="userImage" label="Create Content" />
			<s:submit value="Upload" align="center" /> --%>
	         
	   </div>
	   
	   
	 </div>
	 <input type="hidden" name="topicIdHidden" id="topicIdHidden" value="">
	 <input type="hidden" name="topicVersionHidden" id="topicVersionHidden" value="">
	 <input type="hidden" name="contentTypeHidden" id="contentTypeHidden" value="">
	 </s:form>
	 <div class="uploadContentMainFormUploadButton" id="uploadFileButtonDiv">
	       <button class="myAccountButtons" onclick="ApplicationAJAXServicesJS.CreateContent();">Upload</button>
	 </div>
	 <div class="uploadContentMainFormApproveButton" id="approveContentButtonDiv">
	       <button class="myAccountButtons" onclick="ApplicationAJAXServicesJS.ApproveContent($('#topicIdHidden').val(),$('#topicVersionHidden').val(),$('#contentTypeHidden').val());">Approve</button>
	 </div>
	 
	 <div class="uploadContentFormContentDisplaySection" id="contentDisplayDivId">
	     <c:import url="ContentDisplay.jsp">
		     <c:param name="contentType" value="${param.contentType }"></c:param>
		     <c:param name="directoryPath" value="${param.directoryPath }"></c:param>
		</c:import>
	 </div>
	<div class="applicationCopyRightSection">&#9400; All Rights Reserverd with - Styrish Inc.</div>
   </div>
  </body>
  <script type="text/javascript">
      function changeElement() {
    	  
    	  $('#uploadContentTopicListID').on('change', function () {
    		  
    		    if (this.value == -1) {
    		       $("#newTopicID").attr("type","text");
    		       $("#uploadContentTopicListID").css("display","none");
    		    }
    		});
    	  
      }
      
      function resetTopicSection() {
    	  $('#uploadContentSubjectListID').on('change', function () {
    		  $("#newTopicID").attr("type","hidden");
		       $("#uploadContentTopicListID").css("display","block");
    	  });
    	  
      }
      
      function copyFilePath(oFileInput, sTargetID) {
    	 // alert(document.getElementById(sTargetID).value);
    	   document.getElementById(sTargetID).value = oFileInput.value;
    	    var a = $("#"+sTargetID).val();
    	    a = a.replace("C:\\fakepath\\", "");
    	    //$("#"+sTargetID).val().replace("C:", "");
    	    $("#"+sTargetID).val(a);
    	
    	    
      }
      
      function myFunction() {
    	  //alert(document.getElementById('uploadFilePath').value);
      }
      
      function disableRightClicks() {
    	/*  $("body,#contentIFrameId").on("contextmenu",function(e){
              return false;
         }); */
    	
      }
     
      $(document).ready(function(){
    	 changeElement();
    	 resetTopicSection();
    	 disableRightClicks();
    	
      });
      
      
  
  </script>
</html>