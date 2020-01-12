<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
  <title>Order OK</title>
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
<script src="js/ShoppingCart.js"></script>
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

 
</head>
<body>


 <%@ include file = "mainMenu.jsp"%>

 
    <div class="outerDiv">
     
			<div class="leftDiv">
			   <c:import url="orderItemDetail.jsp">
			      <c:param name="ordersId" value="${param.orderId }"></c:param>
			      <c:param name="pageName" value="orderOK"></c:param>
			    </c:import>
			</div>
			<div class="rightDiv">
				<c:import url="orderAmountDisplay.jsp">
			      <c:param name="ordersId" value="${param.orderId  }"></c:param>
			    </c:import>
				
				<div class="shoppingCartUserDetailsSection">
				    <div>
				       <div class="shopcartUserDetailsContent">Name</div>
				    </div>
				    <div>
				      <div class="shopcartUserDetailsContent">Mobile Number</div>
				    </div>
				    <div>
				      <div class="shopcartUserDetailsContent">Email</div>
				    </div>
				   
				</div>
				
				<div class="orderSummaryDiv">
				    <div style="display: flex;">
				      <div class="orderSummaryDivLeftContent">Payment Method</div>
				      <div class="orderSummaryDivRightContent">Pay TM</div>
				    </div>
				    <div style="display: flex;">
				      <div class="orderSummaryDivLeftContent">Transaction ID</div>
				      <div  class="orderSummaryDivRightContent">123456789</div>
				    </div>
				  
				</div>
			</div>		
		
      </div> 
 <%@ include file = "footer.jsp"%>
</body>
</html>




