package com.styrish.shopping.action;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.shopping.beans.OrderDataBean;
import com.styrish.shopping.dao.OrdersDAOImpl;

public class OrderRefreshAndInitiatePaymentAction extends ActionSupport {

	/**
	 * ===================================================================================================================
	 * This command will have the behaviour as : 1). Refresh order by canceling the
	 * previous order and updating the current logged in user id to new order if
	 * needed. 2). Initiate a payment and redirecting to payment site.
	 * ===================================================================================================================
	 */
	private static final long serialVersionUID = 1L;
	protected Long ordersId;
	protected Long usersId;
	protected String URL;

	@Override
	public String execute() throws Exception {

		moveOrderToLoggedInUser();
		createPaymentProviderURL();
		return "success";
	}

	protected void moveOrderToLoggedInUser() {

		OrderDataBean orderDataBean = new OrderDataBean();
		orderDataBean.setOrdersId(getOrdersId());
		orderDataBean.setAccessProfile("OrderSummaryData");
		OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();

		ordersDAOImpl.fetchOrderDetails(orderDataBean);

		Long usersID = orderDataBean.getUsersId();

		if (usersID != null && !usersID.equals(getUsersId())) {
			// cancel any previous order for the logged in user, the current order will be
			// treted as its new order.
			orderDataBean.resetOrderDataBean();
			orderDataBean.setUsersId(getUsersId());
			ordersDAOImpl.findCurrentPendingOrderForUser(orderDataBean);
			if (orderDataBean.getOrdersId() != null) {
				ordersDAOImpl.cancelOrder(orderDataBean);
			}

			orderDataBean.setOrdersId(getOrdersId());
			ordersDAOImpl.updateOrderOwner(orderDataBean);
		}

	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}

	public Long getUsersId() {
		return usersId;
	}

	public void setUsersId(Long usersId) {
		this.usersId = usersId;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	protected void createPaymentProviderURL() {
     
		String redirectURL = "orderPaymentCallBackAction?ordersId=" + getOrdersId();
		this.setURL(redirectURL);
	}

}
