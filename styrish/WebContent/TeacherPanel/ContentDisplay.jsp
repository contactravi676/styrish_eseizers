<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>


<c:set var="contentType" value="${param.contentType }"/>
<c:set var="contentType" value="Video"/>
<c:set var="filePath" value="${param.filePath }"/>

<c:choose>
   <c:when test="${contentType eq 'Notes'}">
    <iframe src="https://43.231.124.71//contents//Notes//TestPDF.pdf" class="iframeContentStyle" id="contentIFrameId"></iframe>
     <%-- <iframe src="https://43.231.124.71//contents//${filePath}" style="width: 45%;height: 85%; overflow: auto;" id="contentIFrameId"></iframe>  --%>
     
   </c:when>
  <c:when test="${contentType eq 'Video'}">
    <video class="videoContentStyle" controls>
	  <source src="https://43.231.124.71//contents//Video//Aarav_Video Compressed_153.mp4" type="video/mp4">
	  <source src="https://43.231.124.71//contents//Video//Aarav_Video Compressed_153.mp4" type="video/ogg">
     </video>
      <%-- <video width="400" height="240" controls>
		  <source src="//contents//${filePath}" type="video/mp4">
		  <source src="//contents//${filePath}" type="video/ogg">
      </video> --%>
  </c:when>
  <c:when test="${contentType eq 'Excercise'}">
     
  </c:when>

</c:choose>