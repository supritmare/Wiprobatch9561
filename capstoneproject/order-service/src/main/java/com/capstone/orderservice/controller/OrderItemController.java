package com.capstone.orderservice.controller;

import com.capstone.orderservice.model.OrderItemResponse;
import com.capstone.orderservice.payload.OrderItemPayload;
import com.capstone.orderservice.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;

	@PostMapping("/create")
	public ResponseEntity<OrderItemResponse> createOrderItem(@RequestBody OrderItemPayload orderItemPayload) {
		OrderItemResponse orderItemResponse = orderItemService.createOrderItem(orderItemPayload);
		return new ResponseEntity<>(orderItemResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemResponse> getOrderItemDetails(@PathVariable("id") long orderItemId) {
		OrderItemResponse orderItemResponse = orderItemService.getOrderItemDetails(orderItemId);
		return new ResponseEntity<>(orderItemResponse, HttpStatus.OK);
	}
}
