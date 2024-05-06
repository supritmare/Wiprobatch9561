package com.capstone.orderservice.service;

import com.capstone.orderservice.entity.OrderItem;
import com.capstone.orderservice.exception.ResourceNotFoundException;
import com.capstone.orderservice.model.OrderItemResponse;
import com.capstone.orderservice.model.Mobile;
import com.capstone.orderservice.payload.OrderItemPayload;
import com.capstone.orderservice.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private MobileServiceConsumer mobileService;

	@Override
	public OrderItemResponse createOrderItem(OrderItemPayload orderItemPayload) {
		OrderItem orderItem = new OrderItem();
		orderItem.setMobileId(orderItemPayload.getMobileId());
		orderItem.setQuantity(orderItemPayload.getQantity());

		// Save the order item to the database
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);

		// Retrieve mobile details using Feign client
		Mobile mobile = mobileService.getMobileById(savedOrderItem.getMobileId());

		// Construct response
		OrderItemResponse orderItemResponse = new OrderItemResponse();
		orderItemResponse.setOrderItemId(savedOrderItem.getOrderItemId());
		orderItemResponse.setAmount(savedOrderItem.getAmount());
		orderItemResponse.setMobile(mobile);
		orderItemResponse.setQuantity(savedOrderItem.getQuantity());

		return orderItemResponse;
	}

	@Override
	public OrderItemResponse getOrderItemDetails(long orderItemId) {
		// Retrieve order item details from the database
		OrderItem orderItem = orderItemRepository.findById(orderItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Order item not found with id: " + orderItemId));

		// Retrieve mobile details using Feign client
		Mobile mobile = mobileService.getMobileById(orderItem.getMobileId());

		// Construct response
		OrderItemResponse orderItemResponse = new OrderItemResponse();
		orderItemResponse.setOrderItemId(orderItem.getOrderItemId());
		orderItemResponse.setAmount(orderItem.getAmount());
		orderItemResponse.setMobile(mobile);
		orderItemResponse.setQuantity(orderItem.getQuantity());

		return orderItemResponse;
	}
}
