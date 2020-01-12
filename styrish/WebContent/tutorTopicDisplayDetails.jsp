<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Style the tab */
.tab {
  float: left;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
  width: 30%;
  height: 300px;
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
  background-color: inherit;
  color: black;
  padding: 22px 16px;
  width: 100%;
  border: none;
  outline: none;
  text-align: left;
  cursor: pointer;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  float: left;
  padding: 0px 12px;
  border: 1px solid #ccc;
  width: 70%;
  border-left: none;
  height: 300px;
}
</style>
</head>
<body>
 <s:set var="topicId" value="topicId" />
 
 <s:bean name="com.styrish.courses.topics.databean.TopicDocumentDataBean" var="topicDocumentDataBean">
<s:param name="topicId" value="#topicId"></s:param>
<s:set var="documentList" value="documentList" />
</s:bean> 

<s:bean name="com.styrish.courses.topics.databean.TopicVideoDataBean" var="topicVideoDataBean">
<s:param name="topicId" value="#topicId"></s:param>
<s:set var="videoList" value="videoList" />
</s:bean>

<s:bean name="com.styrish.courses.topics.databean.TopicExerciseDataBean" var="topicExerciseDataBean">
<s:param name="topicId" value="#topicId"></s:param>
<s:set var="exerciseList" value="exerciseList" />
</s:bean>
 
 
   
<h2>Topic:<s:property value="topicName"/></h2>


<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'Content')" id="defaultOpen">Content</button>
  <button class="tablinks" onclick="openCity(event, 'Video')">Video</button>
  <button class="tablinks" onclick="openCity(event, 'Exercise')">Exercise</button>
</div>

<div id="Content" class="tabcontent">
            
  <h3>Content</h3>
 
  <p>
  Best Documents for Styrish students.....</br>
            <s:iterator value="#documentList" var="documentListMap">
         Document Title:    <s:property value="#documentListMap['document_title']"/></br>
         Document Path:     <s:property value="#documentListMap['document_path']"/></br>
            </s:iterator> 
  </p>
  
</div>

<div id="Video" class="tabcontent">
  <h3>Video</h3>
  <p>
  Best videos for Styrish students.....
            <s:iterator value="#videoList" var="videoListMap">
          Video Path:          <s:property value="#videoListMap['videos_path']"/></br>
          Video Description     <s:property value="#videoListMap['videos_description']"/></br>
            </s:iterator>
</div>

<div id="Exercise" class="tabcontent">
  <h3>Exercise</h3>
   Best Exercise for Styrish students.....
           <s:iterator value="#exerciseList" var="exerciseListMap">
           
             Question:        <s:property value="#exerciseListMap['question']"/></br>
             Option A:        <s:property value="#exerciseListMap['optionA']"/></br>
             Option B:        <s:property value="#exerciseListMap['optionB']"/></br>
             Option C:        <s:property value="#exerciseListMap['optionC']"/></br>
             Option D:        <s:property value="#exerciseListMap['optionD']"/></br>
             Correct Option:  <s:property value="#exerciseListMap['correctOption']"/></br>
             Hint :           <s:property value="#exerciseListMap['questionHint']"/></br>
             
             ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
           </s:iterator>  
   
  <p>Exercise for Styrish student.</p>
</div>

<script>
function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();
</script>
   
</body>
</html> 
