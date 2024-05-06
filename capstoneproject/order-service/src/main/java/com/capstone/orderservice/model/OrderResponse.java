package com.capstone.orderservice.model;

import java.time.LocalDate;
import java.util.List;

public class OrderResponse {

	private long orderId;
	private double orderTotal;
	private LocalDate orderDate;
	private String orderStatus;
	private Customer customer;
	private List<OrderItemResponse> orderItems;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long l) {
		this.orderId = l;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemResponse> orderItems) {
		this.orderItems = orderItems;
	}

}