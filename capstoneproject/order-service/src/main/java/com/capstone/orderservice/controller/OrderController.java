package com.capstone.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.orderservice.entity.Order;
import com.capstone.orderservice.model.OrderResponse;
import com.capstone.orderservice.payload.OrderItemPayload;
import com.capstone.orderservice.payload.OrderPayload;
import com.capstone.orderservice.service.OrderServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;

	@PostMapping("/save")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderPayload orderPayload) {

		long customerId = orderPayload.getCustomerId();
		List<OrderItemPayload> mobileOrders = orderPayload.getOrderItems();

		Order order = orderService.createOrder(customerId, mobileOrders);
		Order newOrder = orderService.saveOrder(order);

		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> fetchOrderDetails(@PathVariable("id") long orderId) {
		OrderResponse order = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<OrderResponse> fetchAllOrders() {
		List<OrderResponse> orders = orderService.getAllOrders();
		return orders;
	}
}