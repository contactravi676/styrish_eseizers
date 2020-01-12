<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">

<%@ include file="Environment.jsp"%>
<%@ include file="mainMenu.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Quiz</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="./style/instructions.css">

</head>
<body>

<s:set var="mockTestIds" value="mockTestId" />
<s:bean name="com.styrish.mocktest.bean.MockTestDataBean" var="mockTestDataBean">
<s:param name="mockTestId" value="mockTestIds"></s:param>
<s:set var="mockTestMap" value="mockTestMap" /> </s:bean>

    <div id="home">
        <a class="active" href="./home.html">Home</a>
        <a href="javascript:void(0)" id="subject_header"></a>
      </div>
<div class="main" id="main">
    <div id="frame1" role="content" class="container">
     <h2> Instructions </h2>
       <b class="instruction-head">Please read the following instructions very carefully: </b>
       <div id="instruction">${mockTestMap.testInstructions} </div>
    </div>
    <div id="frame2" role="content" class="container">
        <div id="lang_div">
          <p>Choose Your Default language:
          <select id="lang" class="language">
            <option value="eng">ENGLISH</option>
        </select> 
        </p>
      </div>
      <div id="aggrement_div">
        <p>
        <input type="checkbox" name="aggrement" id="aggrement" onchange="change()">I've read all the instructions  carefully and  abide by them.</p>
    </div>
       <a href="./quiz.html"><button class="start-btn-class" id="start_test" class="btn btn-info" >Start Test</button></a>
   </div>
</div>
   </body>

</body>
<script src="./js/home.js"></script>

</html>
