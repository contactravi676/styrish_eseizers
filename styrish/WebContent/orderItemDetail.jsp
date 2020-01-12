<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<jsp:useBean id="orderDetailsMap" type="java.util.Map" class="java.util.HashMap" scope="page"/>

<jsp:useBean id="orderDataBean" class="com.styrish.shopping.beans.OrderDataBean" scope="page">
     <jsp:setProperty name="orderDataBean" property="ordersId" value="${param.ordersId }"/>
     <jsp:setProperty name="orderDataBean" property="accessProfile" value="OrderItemsData"/>
     <jsp:setProperty name="orderDataBean" property="populateOrderDetails" value="${orderDetailsMap }"/>
 </jsp:useBean>

<c:forEach var="orderMap" items="${orderDataBean.populateOrderDetails }">
  <c:set var="orderObj" value="${orderMap.value}"/>
</c:forEach>


<c:if test="${fn:length(orderObj.orderItemDatabeans) eq 0}"><
    <script type="text/javascript">
       window.location.reload();
    </script>
</c:if>
<c:choose>
   <c:when test="${param.pageName eq 'shoppingCart' }">
       <c:set var="headerTitle" value="Shopping Cart"/>
   </c:when>
   <c:when test="${param.pageName eq 'orderOK' }">
       <c:set var="headerTitle" value="Order No # : ${orderDataBean.ordersId}"/>
        <c:set var="headerSubTitle" value="you will also be notified by Watsapp and Email on the information you provided when placing the order."/>
   </c:when>
</c:choose>

				  <div class="shoppingCartHeaderText">${headerTitle}</div>
				  <c:if test="${not empty  headerSubTitle}">
				    <div style="font-size: 12px;font-family: Arial, Helvetica, sans-serif;"><c:out value="${headerSubTitle}"/></div>
				  </c:if>
				<hr>
				   <div class="shopCartHeader">
					   <div class="shopCartContentHeader p_desc">ITEM</div>
					   <div class="shopCartContentHeader p_desc">NAME</div>
					   <div class="shopCartContentHeader p_desc">VALIDITY</div>
					   <div class="shopCartContentHeader p_desc">PRICE</div>
					   
				   </div>
				   <hr>
				    <c:forEach var="orderItem" items="${orderObj.orderItemDatabeans}">
						      <c:set var="itemName" value="${orderItem.coursePackageDetailsDataBean.packDescription}"/>
						      <c:set var="listprice" value="${orderItem.coursePackageDetailsDataBean.listPrice}"/>
						      <c:set var="offerprice" value="${orderItem.coursePackageDetailsDataBean.offerprice}"/>
						      <c:set var="courseSubscription" value="${orderItem.coursePackageDetailsDataBean.courseSubscription}"/>
						      <div class="shopCartContents">
								   <div class="shopCartContentItems"><img alt="" src="img/image_coming_soon_thumbnail.jpg"></div>
								   <div class="shopCartContentItems"><c:out value="${itemName}"/></div>
								   <div class="shopCartContentItems"><c:out value="${courseSubscription}"/></div>
								   <div class="shopCartContentItems">
								     <span class="listPrice">&#8377;<c:out value="${listprice}"/></span><br/>
								     <span>&#8377;<c:out value="${offerprice}"/></span>
								   </div>
				              </div>
				              <div class="shoppingCartRemoveItemSection"><a href="#" class="itemRemoveLink" id="itemRemoveLinkId_${orderItem.orderItemsId}"
				              onclick="ApplicationAJAXServicesJS.RemoveItemFromCartAjax('<c:out value="${orderItem.orderItemsId }"/>','<c:out value="${orderDataBean.ordersId}"/>','<c:out value="${userId }"/>');">Remove</a></div>
				                <div id="loadingmessageItem_${orderItem.orderItemsId}" style='display:none;text-align: right; margin-right: 9%;'> <!--  <img src='img/ajax-loader.png'/> -->
                                    <b><font size="1.5px;">REMOVING...</font></b>
                                </div>
				              <hr>
				     </c:forEach>
                  
				   <div id="myModalItemRemoved" class="modal fade">
				 <div class="modal-dialog modal-confirm">
				        <div class="modal-content" id="itemRemovedModelId">
				            
				            <div class="modal-body">
				                <p class="text-center">Item Removed From Cart.</p>
				            </div>
				            <div class="modal-footer">
				                <button class="btn loginbtn buy_btn" data-toggle="modal" data-target="#myModalItemRemoved">OK</button>
				            </div>
				    </div>
		           </div>
           </div>
	