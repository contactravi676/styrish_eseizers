<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="coursePackDetailsMap" type="java.util.Map" class="java.util.HashMap" scope="request"/>
<html lang="en">
<head>
  <title>Home</title>
  <!-- Start WOWSlider.com HEAD section -->
<link rel="stylesheet" type="text/css" href="engine1/style.css" />
<script type="text/javascript" src="engine1/jquery.js"></script>
<!-- End WOWSlider.com HEAD section -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/explore.css">
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/slider.js"></script>
<script src="js/ProductDetails.js"></script>
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
</head>

 <jsp:useBean id="coursePackDetailsBean" class="com.styrish.courses.packs.databean.CoursePackageDetailsDataBean" scope="request">
     <jsp:setProperty name="coursePackDetailsBean" property="coursePackId" value="${param.packId}"/>
     <jsp:setProperty name="coursePackDetailsBean" property="populatePackDetails" value="${coursePackDetailsMap}"/>
  </jsp:useBean>

		<c:forEach var="packageDetailsMap" items="${coursePackDetailsBean.populatePackDetails}">
		
		  <c:set var="packageDetailsObj" value="${packageDetailsMap.value}"/>
		  <c:set var="packageLongDescription" value="${packageDetailsObj.packLongDescription}"/>
		  <c:set var="packageContents" value="${packageDetailsObj.packageContents}"/>
		  <c:set var="packageDescription" value="${packageDetailsObj.packDescription}"/>
		  <c:set var="courseSubscription" value="${packageDetailsObj.courseSubscription}"/>
		  <c:set var="listPrice" value="${packageDetailsObj.listPrice}"/>
		  <c:set var="offerprice" value="${packageDetailsObj.offerprice}"/>
		  <c:set var="packImagePath" value="${packageDetailsObj.imagePath}"/>
		</c:forEach>


<body>
<c:set var="productId" value="${param.packId }"></c:set>

<%@ include file = "mainMenu.jsp"%>
<!-- Body -->

<input type="hidden" value="" id="orderIdHidden">
<section class="container main_div">
	<div class="p_pic">
		<div class="row">
			<div class="col-md-3 col-sm-4 col-xs-12">
				<img src="<c:out value="${packImagePath }"/>" class="p_img" onerror=this.src="img/image_coming_soon.jpg">
			</div>
			<div class="col-md-9 col-sm-8 col-xs-12" id="addToCartDivSection">
				<p class="p_desc"><c:out value="${packageDescription}"/> (<c:out value="${courseSubscription}"/>)</p>
				<hr style="background: grey;height: 1px">
				<span class="old_price"><c:out value="${listPrice}"/> &#8377;</span>
				<span class="new_price"><c:out value="${offerprice}"/> &#8377;</span>
				<br><br>
				
				<button class="btn loginbtn buy_btn" onclick="ApplicationAJAXServicesJS.AddItem2ShopCartAjax('<c:out value="${productId }"/>','<c:out value="${userId}"/>');">Buy Now</button>
				<!-- <button class="btn loginbtn free_btn">Free Mock</button>  -->
			</div>
			<div id='loadingmessage1' style='display:none'>
               <!--  <img src='img/ajax-loader.png'/> -->
               <b><font size="2px;">PROCESSING REQUEST PLEASE WAIT...</font></b>
            </div>
            <div id="myModal1" class="modal fade">
		    <div class="modal-dialog modal-confirm">
		        <div class="modal-content">
		            <div class="modal-header">
		               <h4 class="modal-title">Great !</h4>
		            </div>
		            <div class="modal-body">
		                <p class="text-center">Your Study Material has been added to your basket.</p>
		            </div>
		            <div class="modal-footer">
		                <button class="btn loginbtn buy_btn" data-toggle="modal" data-target="#myModal1" onclick="Javascript:ProductDetailJS.goToShoppingCart(document.getElementById('orderIdHidden').value);">GO TO PAY</button>
		            </div>
		    </div>
           </div>
          </div>
			
		</div>
			
	</div>
	<%-- <div class="row ">
		<div class="col-md-12 p_desc bod">DESCRIPTION</div>	
	</div>
	<%@ include file="courseProductDescription.jsp"%> --%>
	
	<div class="tab">
	  <button class="tablinks col-md-12 p_desc bod active" onclick="ProductDetailJS.openTab(event, 'aboutPackageTab')" style="width: 25%;">DESCRIPTION</button>
	  <button class="tablinks col-md-12 p_desc bod" onclick="ProductDetailJS.openTab(event, 'packageContentsTab')" style="width: 25%">CONTENTS</button>
	  <button class="tablinks col-md-12 p_desc bod" onclick="ProductDetailJS.openTab(event, 'packageAuthorsTab')" style="width: 25%">AUTHORS</button>
	  <button class="tablinks col-md-12 p_desc bod" onclick="ProductDetailJS.openTab(event, 'faqTab')" style="width: 25%">FAQ</button>
    </div>
    
    <!-- Tab content -->
	<div id="aboutPackageTab" class="tabcontent" style="display: block;">
	  
	    <p class="inner_heading">ABOUT PACKAGE</p>
		<p class="inner_p">${packageLongDescription }</p> 
	</div>
	
	<div id="packageContentsTab" class="tabcontent">
	   <p class="inner_heading">PACKAGE INCLUDES</p> 
	   <p class="inner_p">
	   
	    <c:set var = "contentsArray" value = "${fn:split(packageContents, '_')}" />
	        <c:forEach var="content" items="${contentsArray}">
		       ${content }<br/>
		    </c:forEach>
	   
	   </p>
    </div>
	
	<div id="packageAuthorsTab" class="tabcontent">
	  <h3>About Teachers</h3>
	  <p>Tokyo is the capital of Japan.</p>
	</div>
	
	
	<div id="faqTab" class="tabcontent">
	 FAQ
	</div>
	
</section>

<%@ include file = "footer.jsp"%>


</body>

<script type="text/javascript">
	/* function AddItem2ShopCartAjax(productId,userId) {
		var productId = productId;
		var userId = userId;
		$('.btn').hide();
		$('#loadingmessage1').show();
	    
		$.ajax({
			type : "POST",
			url : "ajax/AjaxOrderItemAddAction",
			data : "productId=" + productId + "&userId=" +userId,
			dataType: 'json',
			success : function(data) {
				var ht = data.msg;
				$("#resp").html(ht);
				$('#loadingmessage1').hide();
				$('#miniShopCartist').load('miniShopCartContent.jsp');
				$("#myModal1").modal('show');
				$('.btn').show();
				$('#orderIdHidden').val(data.ordersId);
			},
			error : function(data) {
				alert("Some error occured.");
			}
		});
	} */
	/* function goToShoppingCart(ordersId) {
	      window.location.href = "shoppingCartDetailAction?ordersId="+ordersId;
	    } 
	  */
	
</script>
<script type="text/javascript">
/* function openTab(evt, tabName) {
	  // Declare all variables
	  var i, tabcontent, tablinks;

	  // Get all elements with class="tabcontent" and hide them
	  tabcontent = document.getElementsByClassName("tabcontent");
	  for (i = 0; i < tabcontent.length; i++) {
	    tabcontent[i].style.display = "none";
	  }

	  // Get all elements with class="tablinks" and remove the class "active"
	  tablinks = document.getElementsByClassName("tablinks");
	  for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	  }

	  // Show the current tab, and add an "active" class to the button that opened the tab
	  document.getElementById(tabName).style.display = "block";
	  evt.currentTarget.className += " active";
	}

 */
</script>

</html>
