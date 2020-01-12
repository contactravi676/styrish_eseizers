<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="orderDetailsMap" type="java.util.Map" class="java.util.HashMap" scope="page"/>

<jsp:useBean id="orderDataBean" class="com.styrish.shopping.beans.OrderDataBean" scope="page">
     <jsp:setProperty name="orderDataBean" property="ordersId" value="${param.ordersId }"/>
     <jsp:setProperty name="orderDataBean" property="accessProfile" value="OrderSummaryData"/>
     <jsp:setProperty name="orderDataBean" property="populateOrderDetails" value="${orderDetailsMap }"/>
 </jsp:useBean>
    <div style="display: flex;">
		<div class="orderSummaryDivLeftContent">Order SubTotal</div>
		<div class="orderSummaryDivRightContent">&#8377;<c:out value="${orderDataBean.productTotal}"/></div>
	 </div>
	<div style="display: flex;">
		<div class="orderSummaryDivLeftContent">Order Tax</div>
		<div  class="orderSummaryDivRightContent">&#8377;<c:out value="${orderDataBean.orderTax}"/></div>
	</div>
	<div style="display: flex;">
	    <div class="orderSummaryDivLeftContent">Discount</div>
	    <div  class="orderSummaryDivRightContent">-&#8377;<c:out value="${orderDataBean.orderDiscount}"/></div>
	</div>
	<div style="display: flex;">
	    <div class="orderSummaryDivLeftContent">Grand Total</div>
	    <div  class="orderSummaryDivRightContent">&#8377;<c:out value="${orderDataBean.orderTotal}"/></div>
	</div>
				