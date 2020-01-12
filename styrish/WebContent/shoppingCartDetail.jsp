<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
  <title>Shopping Cart</title>
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
     <c:choose>
      <c:when test="${noOfItemsInOrder gt 0}">
			<div class="leftDiv" id="shoppingCartLeftDivId">
			  <div class="shopCartDiv" id="shopCartDivId">
				   <c:import url="orderItemDetail.jsp">
				      <c:param name="ordersId" value="${pendingOrderId }"></c:param>
				      <c:param name="pageName" value="shoppingCart"></c:param>
				    </c:import>
			   </div>
			</div>
			<div class="rightDiv">
			     <div class="orderSummaryDiv" id="orderSummaryDivId">
					<c:import url="orderAmountDisplay.jsp">
				      <c:param name="ordersId" value="${pendingOrderId }"></c:param>
				    </c:import>
			      </div>
				<div class="shoppingCartApplyDiscountSection">
				   <div style="width:70%;"><input type="text" name="applyDiscountTextBox" placeholder="Apply Discount Code" class="applyDiscountTextBox"></div>
				   <div style="width:20%"> <button class="btn loginbtn buy_btn">APPLY</button></div>
				</div>
				<div class="shoppingCartUserDetailsSection">
				    <div>
				       <div class="shopcartUserDetailsContent"><input type="text" name="applyDiscountTextBox" placeholder="FULL NAME" class="shopcartUserDetailsTextBox"></div>
				    </div>
				    <div>
				      <div class="shopcartUserDetailsContent"><input type="text" name="applyDiscountTextBox" placeholder="MOBILE NUMBER" class="shopcartUserDetailsTextBox"></div>
				    </div>
				    <div>
				      <div class="shopcartUserDetailsContent"><input type="text" name="applyDiscountTextBox" placeholder="EMAIL" class="shopcartUserDetailsTextBox"></div>
				    </div>
				   
				</div>
				
				<div class="shoppingCartPayButtonSection">
				 <c:choose>
				   <c:when test="${sessionScope.userType eq 'R' }">
				     <c:url var="orderRefreshAndInitiatePaymentActionUrl" value="orderRefreshAndInitiatePaymentAction">
				         <c:param name="usersId" value="${sessionScope.userId }"></c:param>
				         <c:param name="ordersId" value="${pendingOrderId }"></c:param>
				     </c:url>
				    <div class="shoppingCartPayButtonContent"><button class="btn loginbtn buy_btn" onclick="ShoppingCartJS.forwardToPay('${orderRefreshAndInitiatePaymentActionUrl}');">PROCEEED TO PAY</button></div>
				   </c:when>
				   <c:when test="${sessionScope.userType eq 'G' }">
				      <div class="shoppingCartPayButtonContent">
				        <button class="btn loginbtn buy_btn"  data-toggle="modal" data-target="#myModal" id="proceedToPay" onclick="LoginRegisterModalWindowJS.setRedirectURL('orderRefreshAndInitiatePaymentAction?ordersId=${pendingOrderId}');">PROCEEED TO PAY</button>
				      </div>
				   </c:when>
				 </c:choose>
				   
				</div>
			</div>		
			<!-- <div "style: clear:both;"></div>  -->
			 </c:when>
			 <c:otherwise>
			     <div style="text-align: center;min-height: 50px;"><font style="font-family: Arial, Helvetica, sans-serif;font-size: 20px;color: #FF4081;">
			     Your Shopping Cart Is Empty, Buy Some Study Packages..</font></div>
			 </c:otherwise>
		 </c:choose>
      </div> 
 <%@ include file = "footer.jsp"%>
</body>
</html>




