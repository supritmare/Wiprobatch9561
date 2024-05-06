package com.capstone.orderservice.payload;

import java.util.ArrayList;
import java.util.List;

public class OrderPayload {

	private long customerId;
	private List<OrderItemPayload> orderItems = new ArrayList<>();

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemPayload> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemPayload> orderItems) {
		this.orderItems = orderItems;
	}

}
