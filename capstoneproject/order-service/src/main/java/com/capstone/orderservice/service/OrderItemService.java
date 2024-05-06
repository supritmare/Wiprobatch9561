package com.capstone.orderservice.service;
import com.capstone.orderservice.model.OrderItemResponse;
import com.capstone.orderservice.payload.OrderItemPayload;

public interface OrderItemService {

	OrderItemResponse createOrderItem(OrderItemPayload orderItemPayload);

	OrderItemResponse getOrderItemDetails(long orderItemId);
}
