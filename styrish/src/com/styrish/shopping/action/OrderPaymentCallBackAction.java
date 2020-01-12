package com.styrish.shopping.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.styrish.shopping.helper.ProcessOrderTaskCmd;

public class OrderPaymentCallBackAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long ordersId;
	protected Map<String,String> paymentResponse;
	protected String URL;
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Map<String, String> getPaymentResponse() {
		return paymentResponse;
	}
	public void setPaymentResponse(Map<String, String> paymentResponse) {
		this.paymentResponse = paymentResponse;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	@Override
	public String execute() throws Exception {
		
		ProcessOrderTaskCmd processOrderTaskCmd = new ProcessOrderTaskCmd();
		processOrderTaskCmd.setOrdersId(getOrdersId());
		processOrderTaskCmd.setAttributeData(getPaymentResponse());
		processOrderTaskCmd.execute();
		
		setURL("OrderOK.jsp?orderId="+getOrdersId());
		return "success";
	}
	
	
	
	
	
	
	
	
	

}
