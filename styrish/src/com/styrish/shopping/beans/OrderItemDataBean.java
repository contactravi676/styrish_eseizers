package com.styrish.shopping.beans;

import com.styrish.courses.packs.databean.CoursePackageDetailsDataBean;

public class OrderItemDataBean {

	protected Long orderItemsId;
	protected Long ordersId;
	protected String itemCode;
	protected Long itemId;
	protected CoursePackageDetailsDataBean coursePackageDetailsDataBean;
	protected Double totalProducts;
	protected Double discount;
	protected Double grandTotal;

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

	public CoursePackageDetailsDataBean getCoursePackageDetailsDataBean() {
		return coursePackageDetailsDataBean;
	}

	public void setCoursePackageDetailsDataBean(CoursePackageDetailsDataBean coursePackageDetailsDataBean) {
		this.coursePackageDetailsDataBean = coursePackageDetailsDataBean;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getTotalProducts() {
		return totalProducts;
	}

	public void setTotalProducts(Double totalProducts) {
		this.totalProducts = totalProducts;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
