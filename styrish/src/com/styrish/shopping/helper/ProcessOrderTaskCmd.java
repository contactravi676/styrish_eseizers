package com.styrish.shopping.helper;

import java.util.Map;

import com.styrish.shopping.beans.OrderDataBean;
import com.styrish.shopping.dao.OrdersDAOImpl;

public class ProcessOrderTaskCmd {
	
	protected Long ordersId;
	protected Map<String,String> attributeData;
	protected OrderDataBean orderDataBean;
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Map<String, String> getAttributeData() {
		return attributeData;
	}
	public void setAttributeData(Map<String, String> attributeData) {
		this.attributeData = attributeData;
	}
	public OrderDataBean getOrderDataBean() {
		return orderDataBean;
	}
	public void setOrderDataBean(OrderDataBean orderDataBean) {
		this.orderDataBean = orderDataBean;
	}
	
	
	public void execute() throws Exception {
		
		Long ordersId = getOrdersId();
		if (ordersId != null) {
			this.orderDataBean = new OrderDataBean();
			OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
			orderDataBean.setStatus('C');
			orderDataBean.setOrdersId(ordersId);
			ordersDAOImpl.completeOrder(orderDataBean);
			
		}
		
	}
	


}
