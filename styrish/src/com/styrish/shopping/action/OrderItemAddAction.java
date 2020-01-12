package com.styrish.shopping.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.commons.util.objects.CommonUtils;
import com.styrish.shopping.beans.OrderDataBean;
import com.styrish.shopping.dao.OrdersDAOImpl;

public class OrderItemAddAction extends ActionSupport{

	/** This command is used for adding course packages , individual study material, test series etc. to user cart.
	 *  This command is called in Ajax fashion from JSP.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long ordersId;
	private Long orderItemId;
	private Long productId;
	private Long userId;
	private String productType;
	private int quantity;
	
	
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
	@Override
	public String execute() throws Exception {
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
		Long currentOrderId = ordersDAOImpl.findCurrentPendingOrderForUser(getUserId());
		
		if (currentOrderId != null) {
			setOrdersId(currentOrderId);
			ordersDAOImpl.createOrderItem(this);
		}else{
			ordersDAOImpl.createOrder(this);
			Long currentOrderIdVar = ordersDAOImpl.findCurrentPendingOrderForUser(getUserId());
			setOrdersId(currentOrderIdVar);
			ordersDAOImpl.createOrderItem(this);
		}
		OrderDataBean orderDataBean = new OrderDataBean();
		orderDataBean.setOrdersId(getOrdersId());
		ordersDAOImpl.refreshOrderAmounts(orderDataBean);
		StringBuilder cacheIdentifier = new StringBuilder("noOfItemsInOrder").append("_")
				.append(getUserId());
		CommonUtils.invalidateCache(cacheIdentifier);
	
		return "success";
	}
	
	

}
