package com.styrish.shopping.action;

import com.opensymphony.xwork2.ActionSupport;

public class ShoppingCartDetailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ordersId;

	@Override
	public String execute() throws Exception {
		return "success";
	}

	public Long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
    
}
