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
<s:bean name="com.styrish.courses.databean.CourseTopicComponentDataBean" var="courseTopicComponentDataBean">
<s:param name="topicId" value="#topicId"></s:param>
<s:set var="topicVideoMap" value="topicVideoMap" />
<s:set var="topicContentMap" value="topicContentMap" />
<s:set var="topicExerciseList" value="topicExerciseList" />
 </s:bean> 
   jjjjjjjjjjjjjjjj <s:property value='topicId'/>
<h2>Vertical Tabs</h2>
<p>Click on the buttons inside the tabbed menu:</p>

<div class="tab">
  <button class="tablinks" onclick="openCity(event, 'Content')" id="defaultOpen">Content</button>
  <button class="tablinks" onclick="openCity(event, 'Video')">Video</button>
  <button class="tablinks" onclick="openCity(event, 'Exercise')">Exercise</button>
</div>

<div id="Content" class="tabcontent">
            
  <h3>Content</h3>
  <p>
    <s:iterator value="#topicContentMap" var="topicContentMapEntry">
              <s:property value='#topicContentMapEntry.key'/> 
              <s:property value='#topicContentMapEntry.value' /></br>
            </s:iterator> 
  Content for Styrish student.</p>
  
   <s:property value="topicId" />
</div>

<div id="Video" class="tabcontent">
  <h3>Video</h3>
  <p>
  Best videos for Styrish students.....
  <s:iterator value="#topicVideoMap" var="topicVideoMapEntry">
              <s:property value='#topicVideoMapEntry.key'/> 
              <s:property value='#topicVideoMapEntry.value' />
            </s:iterator> 
  </p> 
</div>

<div id="Exercise" class="tabcontent">
  <h3>Exercise</h3>
   
   <s:iterator value="#topicExerciseList" var="topicExerciseMap">
   <h5>Question</h5> 
     <s:iterator value="#topicExerciseMap" var="topicExerciseMapEntry">
      <s:property value='#topicExerciseMapEntry.key' />:::::::
            <s:property value='#topicExerciseMapEntry.value' /><br>
            </s:iterator> 
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
