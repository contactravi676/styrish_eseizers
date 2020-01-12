package com.styrish.shopping.beans;

import java.util.List;
import java.util.Map;

import com.styrish.commons.util.objects.CacheManager;
import com.styrish.commons.util.objects.CachedObject;
import com.styrish.shopping.dao.OrdersDAOImpl;

public class OrderDataBean {
	
	protected Long ordersId;
    protected List<OrderItemDataBean> orderItemDatabeans;
    protected Long usersId;
    protected Double orderTotal;
    protected Double orderDiscount;
    protected Double productTotal;
    protected Double orderTax;
    protected int noOfItemsInOrder;
    protected List<OrderItemDataBean> ordItemDataBeans;
    protected Map<Long,OrderDataBean> populateOrderDetails;
    protected String accessProfile;
    protected int itemCount;
    protected char status;
    
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public List<OrderItemDataBean> getOrderItemDatabeans() {
		return orderItemDatabeans;
	}
	public void setOrderItemDatabeans(List<OrderItemDataBean> orderItemDatabeans) {
		this.orderItemDatabeans = orderItemDatabeans;
	}
	public Long getUsersId() {
		
		return usersId;
	}
	public void setUsersId(Long usersId) {
		
		this.usersId = usersId;
	}
	public Double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Double getOrderDiscount() {
		return orderDiscount;
	}
	public void setOrderDiscount(Double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}
	public Double getOrderTax() {
		return orderTax;
	}
	public void setOrderTax(Double orderTax) {
		this.orderTax = orderTax;
	}
	public int getNoOfItemsInOrder() {
		return noOfItemsInOrder;
	}
	
	public void setNoOfItemsInOrder(int noOfItemsInOrder) {
		StringBuilder cacheIdentifier = new StringBuilder("noOfItemsInOrder").append("_")
				.append(getUsersId());
		Object identifierObj = cacheIdentifier.toString();
		 OrderDataBean orderDataBean = null;
		
		CachedObject o = (CachedObject) CacheManager.getCache(identifierObj.toString());
		if (o == null) {
			OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		    orderDataBean = ordersDAOImpl.findNumberOfItemsInCurrentShoppingCart(getUsersId());
		    CachedObject cachedObject = new CachedObject(orderDataBean, identifierObj, 180);
		    CacheManager.putCache(cachedObject);
		} else {
			orderDataBean = (OrderDataBean)o.object;
		}
	    noOfItemsInOrder = orderDataBean.getItemCount();
	    this.setOrdersId(orderDataBean.getOrdersId());
		this.noOfItemsInOrder = noOfItemsInOrder;
	}
	public List<OrderItemDataBean> getOrdItemDataBeans() {
		return ordItemDataBeans;
	}
	public void setOrdItemDataBeans(List<OrderItemDataBean> ordItemDataBeans) {
		this.ordItemDataBeans = ordItemDataBeans;
	}
	public Map<Long, OrderDataBean> getPopulateOrderDetails() {
		return populateOrderDetails;
	}
	public void setPopulateOrderDetails(Map<Long, OrderDataBean> populateOrderDetails) {
		
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		ordersDAOImpl.fetchOrderDetails(this);
		OrderDataBean orderDataBean = ordersDAOImpl.getOrderDataBean();
		if (orderDataBean != null) {
			populateOrderDetails.put(orderDataBean.getOrdersId(), orderDataBean);
		}
		this.populateOrderDetails = populateOrderDetails;
	}
	public Double getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(Double productTotal) {
		this.productTotal = productTotal;
	}
	public String getAccessProfile() {
		return accessProfile;
	}
	public void setAccessProfile(String accessProfile) {
		this.accessProfile = accessProfile;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	public void resetOrderDataBean() {
		
		setOrdersId(null);
		setUsersId(null);
		setOrderDiscount(null);
		setOrderItemDatabeans(null);
		setAccessProfile(null);
		setItemCount(0);
		
		setOrderTax(null);
		setOrderTotal(null);
		
		setProductTotal(null);
		
		
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	
    
  
}
