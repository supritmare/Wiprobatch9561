package com.capstone.orderservice.service;

import java.util.List;

import com.capstone.orderservice.entity.Order;
import com.capstone.orderservice.model.OrderResponse;
import com.capstone.orderservice.payload.OrderItemPayload;

public interface OrderService {

	Order createOrder(long customerId, List<OrderItemPayload> mobileOrders);

	Order saveOrder(Order order);

	OrderResponse getOrderDetails(long orderId);

	List<OrderResponse> getAllOrders();

}