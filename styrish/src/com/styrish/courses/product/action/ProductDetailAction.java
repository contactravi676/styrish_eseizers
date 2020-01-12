package com.styrish.courses.product.action;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String packId;
	private List<Map<String, String>> productDetailsList;

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public List<Map<String, String>> getProductDetailsList() {
		return productDetailsList;
	}

	public void setProductDetailsList(List<Map<String, String>> productDetailsList) {
		this.productDetailsList = productDetailsList;
	}

	public String execute() {

		return SUCCESS;
	}

}
