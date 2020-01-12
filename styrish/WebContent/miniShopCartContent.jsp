<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="userIdSession" value="${sessionScope.userId}"/>
<c:if test="${userIdSession eq null}">
  <c:set var="userIdSession" value="-1002"/>
</c:if>

  <jsp:useBean id="orderDataBean" class="com.styrish.shopping.beans.OrderDataBean" scope="page">
     <jsp:setProperty name="orderDataBean" property="usersId" value="${userIdSession}"/>
     <jsp:setProperty name="orderDataBean" property="noOfItemsInOrder" value="0"/>
  </jsp:useBean>
 
 <c:url var="shoppingCartUrl" value='shoppingCartDetailAction'>
  </c:url>
  <a href="${shoppingCartUrl }"><font color="white"><strong>${orderDataBean.noOfItemsInOrder } ITEMS</strong></font></a>
  <c:set var="pendingOrderId" value="${orderDataBean.ordersId}"/>
  <c:set var="noOfItemsInOrder" value="${orderDataBean.noOfItemsInOrder  }"/>