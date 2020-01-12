package com.styrish.shopping.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.commons.util.objects.CommonUtils;
import com.styrish.shopping.beans.OrderDataBean;
import com.styrish.shopping.beans.OrderItemDataBean;
import com.styrish.shopping.dao.OrdersDAOImpl;

public class OrderItemDeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long orderItemsId;
	protected Long ordersId;
	protected Long usersId;

	public Long getOrderItemsId() {
		return orderItemsId;
	}

	public void setOrderItemsId(Long orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	@Override
	public String execute() throws Exception {
		OrderItemDataBean orderItemDataBean = new OrderItemDataBean();
		orderItemDataBean.setOrderItemsId(getOrderItemsId());
		OrderDataBean orderDataBean = new OrderDataBean();
		orderDataBean.setOrdersId(getOrdersId());
		removeOrderItem(orderItemDataBean, orderDataBean);
		StringBuilder cacheIdentifier = new StringBuilder("noOfItemsInOrder").append("_")
				.append(getUsersId());
		CommonUtils.invalidateCache(cacheIdentifier);
		return "success";
	}

	protected void removeOrderItem(OrderItemDataBean orderItemDataBean, OrderDataBean orderDataBean) {

		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		ordersDAOImpl.deleteOrderItem(orderItemDataBean);
		ordersDAOImpl.refreshOrderAmounts(orderDataBean);

	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}
	
	

}
