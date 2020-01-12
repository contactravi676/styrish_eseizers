<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: "Lato", sans-serif;
}

/* Fixed sidenav, full height */
.sidenav {
  height: 100%;
  width: 200px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 20px;
}

/* Style the sidenav links and the dropdown button */
.sidenav a, .dropdown-btn {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 20px;
  color: #818181;
  display: block;
  border: none;
  background: none;
  width: 100%;
  text-align: left;
  cursor: pointer;
  outline: none;
}

/* On mouse-over */
.sidenav a:hover, .dropdown-btn:hover {
  color: #f1f1f1;
}

/* Main content */
.main {
  margin-left: 200px; /* Same as the width of the sidenav */
  font-size: 20px; /* Increased text to enable scrolling */
  padding: 0px 10px;
}

/* Add an active class to the active dropdown button */
.active {
  background-color: green;
  color: white;
}

/* Dropdown container (hidden by default). Optional: add a lighter background color and some left padding to change the design of the dropdown content */
.dropdown-container {
  display: none;
  background-color: #262626;
  padding-left: 8px;
}

/* Optional: Style the caret down icon */
.fa-caret-down {
  float: right;
  padding-right: 8px;
}

/* Some media queries for responsiveness */
@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
</style>
</head>
<body>

<s:set var="courseId" value="courseId" />
<s:bean name="com.styrish.courses.databean.CourseSubjectDataBean" var="courseSubjectDataBean">
<s:param name="courseId" value="#courseId"></s:param>
<s:set var="courseSubjectList" value="courseSubjectList" /> </s:bean> 



<div class="sidenav">

 <s:iterator value="#courseSubjectList" var="courseSubjectListMap">
 <s:set var="subjectId" value="#courseSubjectListMap['coursesubject_id']" />
 <button class="dropdown-btn"><s:property value="#courseSubjectListMap['subjectname']"/>
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
  
  <s:bean name="com.styrish.courses.databean.CourseTopicsDataBean" var="courseTopicsDataBean">
<s:param name="courseId" value="#courseId"></s:param>
<s:param name="subjectId" value="subjectId"></s:param>
<s:set var="courseTopicList" value="courseTopicList" /> </s:bean> 
  <s:iterator value="#courseTopicList" var="courseTopicListMap">
  <a href="courseTopicComponent?topicId=<s:property value="#courseTopicListMap['coursetopic_id']" />"><s:property value="#courseTopicListMap['topic_name']"/></a>
  </s:iterator>   
   </div>
  
  </s:iterator>
  
 </div>

<div class="main">
  <h2>Sidebar Dropdown</h2>
  <p>Click on the dropdown button to open the dropdown menu inside the side navigation.</p>
  <p>This sidebar is of full height (100%) and always shown.</p>
  <p>Some random text..</p>
</div>

<script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown = document.getElementsByClassName("dropdown-btn");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}
</script>

</body>
</html> 
