<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html lang="en">
<head>
  <title>Home</title>
  <!-- Start WOWSlider.com HEAD section -->
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css"><link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/explore.css"><script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script><script src="js/tooltip.js"></script>
<script src="js/slider.js"></script>
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
</head>
<body>
<html lang="en">
<s:set var="courseId" value="courseId" />
<s:bean name="com.styrish.courses.packs.databean.CoursePacksDataBean" var="coursePacksDataBean">
<s:param name="courseId" value="#courseId"></s:param>
<s:set var="coursePackList" value="coursePackList" /> </s:bean> 


<!-- Body -->

<%@ include file = "mainMenu.jsp"%>
<section class="container">
	<h2 align="center">Railways Preparation</h2>
	<h3>All Packages</h3>
		
<div class="row">
		<div class="MultiCarousel" data-items="1,3,5,6" data-slide="1" id="MultiCarousel"  data-interval="1000">
            <div class="MultiCarousel-inner">
            
            <s:iterator value="#coursePackList" var="coursePackMap">
       <s:set var="pckageType" value="#coursePackMap['pack_type']" />     
            <c:if test="${pckageType eq 'complete'}">
 
  <div class="item">
                    <div>
        <a href="productDetailAction?packId=<s:property value="#coursePackMap['coursepacks_id']"/>"><img src="<s:property value="#coursePackMap['packimage_path']"/>" style="height:150px;width:100%"></a>
                        <p><s:property value="#coursePackMap['packname']"/></p>
                        <span class="price_left"><s:property value="#coursePackMap['listprice']"/></span>
						<span class="price_right"><s:property value="#coursePackMap['offerprice']"/></span>   
					<br style="clear:both">
                    </div>
                </div>
      </c:if>          
 </s:iterator>
            
         
            </div>
            <button class="btn btn-primary leftLst"><</button>
            <button class="btn btn-primary rightLst">></button>
        </div>
	</div>


<h3>Study Materials</h3>

		
<div class="row">
		<div class="MultiCarousel" data-items="1,3,5,6" data-slide="1" id="MultiCarousel"  data-interval="1000">
            <div class="MultiCarousel-inner">
             <s:iterator value="#coursePackList" var="coursePackMap">
       <s:set var="pckageType" value="#coursePackMap['pack_type']" />     
            <c:if test="${pckageType eq 'content'}">
                <div class="item">
                    <div>
                        <img src="<s:property value="#coursePackMap['packimage_path']"/>" style="height:150px;width:100%">
                       <p><s:property value="#coursePackMap['packname']"/></p>
                        <span class="price_left"><s:property value="#coursePackMap['listprice']"/></span>
						<span class="price_right"><s:property value="#coursePackMap['offerprice']"/></span>    
					<br style="clear:both">
                    </div>
                </div>
                 </c:if>          
 </s:iterator> 
				
            </div>
            <button class="btn btn-primary leftLst"><</button>
            <button class="btn btn-primary rightLst">></button>
        </div>
	</div>

<h3>Mock Tests</h3>

		
<div class="row">
		<div class="MultiCarousel" data-items="1,3,5,6" data-slide="1" id="MultiCarousel"  data-interval="1000">
            <div class="MultiCarousel-inner">
             <s:iterator value="#coursePackList" var="coursePackMap">
       <s:set var="pckageType" value="#coursePackMap['pack_type']" />     
            <c:if test="${pckageType eq 'mock'}">
                <div class="item">
                    <div>
                        <img src="<s:property value="#coursePackMap['packimage_path']"/>" style="height:150px;width:100%">
                       <p><s:property value="#coursePackMap['packname']"/></p>
                        <span class="price_left"><s:property value="#coursePackMap['listprice']"/></span>
						<span class="price_right"><s:property value="#coursePackMap['offerprice']"/></span>
					<br style="clear:both">
                    </div>
                </div>
                </c:if>          
 </s:iterator>  
				
            </div>
            <button class="btn btn-primary leftLst"><</button>
            <button class="btn btn-primary rightLst">></button>
        </div>
	</div>
</section>
<!-- Dates Of Exams -->

<section class="container" style="background:">
	<h2 align="center">List of Railway Exams 2019</h2>
	<div class="row exam_description">
		<div class="col-md-12" align="center">
		<b>Railway Recruitment Board offers a large number of vacancies to candidates in various 
		departments in Indian Railways. Posts are offered region wise and candidates have the opportunity to 
		join the Railways to the post of ALP, Technician, Group D Officers, Police Force, 
		Non-Technical Posts etc.<b>
		</div>
	</div>
	
	<div class="jumbotron">
		<div class="row exam_heading">
			<div class="col-md-4 col-sm-4 col-xs-4">Exam Name</div>
			<div class="col-md-4 col-sm-4 col-xs-4">Important Dates</div>
			<div class="col-md-4 col-sm-4 col-xs-4">Link (Button)</a></div>
		</div><hr>
		<div class="row exam_details">
			<div class="col-md-4 col-sm-4 col-xs-4">Railway ALP 2019</div>
			<div class="col-md-4 col-sm-4 col-xs-4">To be announced</div>
			<div class="col-md-4 col-sm-4 col-xs-4"><a href="">click Here</a></div>		
		</div><hr>
		<div class="row exam_details" >
			<div class="col-md-4 col-sm-4 col-xs-4">Railway ALP 2019</div>
			<div class="col-md-4 col-sm-4 col-xs-4">To be announced</div>
			<div class="col-md-4 col-sm-4 col-xs-4"><a href="">click Here</a></div>
		</div>	
	</div>	
</section>

<%@ include file = "footer.jsp"%>

</body>
</html>















