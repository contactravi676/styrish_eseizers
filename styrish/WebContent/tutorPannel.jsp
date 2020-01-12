<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Styrish..........</title>

</head>

<s:include value="/header.jsp"></s:include>
<body>


	<s:set var="mocketesid" value=" " />
	<s:set var="courseId" value="13" />
	<s:set var="mockStatus" value="'P'" />
	<s:bean name="com.styrish.mocktest.bean.MockTestDataBean"
		var="mockTestDataBean">
		<s:param name="courseId" value="%{courseId}"></s:param>
		<s:param name="mockTestStatus" value="%{mockStatus}"></s:param>
		<s:set var="mockTestLists" value="mockTestList" />
	</s:bean>


	<s:iterator value="#mockTestLists" var="mockTestListMap">
		<s:set var="mocketesid" value="#mockTestListMap['mocktest_id']" />
		<s:property value="#mockTestListMap['mocktest_id']" />
	</s:iterator>





	<div>
	     <div>
			<a href="tutorTopicCreatedResult.jsp">Create Topics</a>
		</div>
		</br>
	     <div>
			<a href="tutorTopicContentCreate.jsp">Create Content</a>
		</div>
		</br>
		<div>
			<a href="tutorTopicContentUpload.jsp">Upload Content</a>
		</div>
		</br>
		<div>
			<a href="tutorTopicVideoUpload.jsp">Upload Video</a>
		</div>
		</br>
		<div>
			<a href="tutorTopicExerciseAdd.jsp">Create Exercise</a>
		</div>
		</br>

		<c:choose>
			<c:when test="${empty mocketesid}">
				<div>
					<a href="tutorMockTestCreate.jsp">Add Mock Test</a>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<a href="tutorMockTestDisplay.jsp">Add Mock Test</a>
				</div>
			</c:otherwise>
		</c:choose>
		
		</br><div>
			<a href="tutorTopicDisplay.jsp">View Course Content</a>
		</div>
	</div>
</body>
<s:include value="/footer.jsp"></s:include>
</html>
