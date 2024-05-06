package com.capstone.orderservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.orderservice.entity.Order;
import com.capstone.orderservice.entity.OrderItem;
import com.capstone.orderservice.exception.ResourceNotFoundException;
import com.capstone.orderservice.model.Customer;
import com.capstone.orderservice.model.Mobile;
import com.capstone.orderservice.model.OrderItemResponse;
import com.capstone.orderservice.model.OrderResponse;
import com.capstone.orderservice.payload.OrderItemPayload;
import com.capstone.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private MobileServiceConsumer mobileService;

	@Autowired
	private CustomerServiceConsumer customerService;

	@Override
	public Order saveOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found with id: " + orderId);
		}
		Order order = optionalOrder.get();

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderTotal(order.getOrderTotal());
		orderResponse.setOrderStatus(order.getOrderStatus());

		long customerId = order.getCustomerId();
		Customer customer = customerService.getCustomerDetails(customerId);

		orderResponse.setCustomer(customer);

		List<OrderItemResponse> orderItems = new ArrayList<>();

		List<OrderItem> oitems = order.getOrderItems();

		for (OrderItem oi : oitems) {
			OrderItemResponse oitemResp = new OrderItemResponse();
			oitemResp.setOrderItemId(oi.getOrderItemId());
			oitemResp.setAmount(oi.getAmount());

			long mid = oi.getMobileId();
			Mobile mobile = mobileService.getMobileById(mid);
			oitemResp.setMobile(mobile);

			orderItems.add(oitemResp);
		}

		orderResponse.setOrderItems(orderItems);

		return orderResponse;
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orderList = orderRepository.findAll();

		List<OrderResponse> orderResponses = new ArrayList<>();

		for (Order order : orderList) {
			OrderResponse orderResponse = mapOrderToResponse(order);
			orderResponses.add(orderResponse);
		}

		return orderResponses;
	}

	private OrderResponse mapOrderToResponse(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderTotal(order.getOrderTotal());
		orderResponse.setOrderStatus(order.getOrderStatus());

		Customer customer = customerService.getCustomerDetails(order.getCustomerId());
		orderResponse.setCustomer(customer);

		List<OrderItemResponse> orderItemResponses = new ArrayList<>();
		for (OrderItem orderItem : order.getOrderItems()) {
			OrderItemResponse orderItemResponse = new OrderItemResponse();
			orderItemResponse.setOrderItemId(orderItem.getOrderItemId());
			orderItemResponse.setAmount(orderItem.getAmount());

			Mobile mobile = mobileService.getMobileById(orderItem.getMobileId());
			orderItemResponse.setMobile(mobile);

			orderItemResponses.add(orderItemResponse);
		}
		orderResponse.setOrderItems(orderItemResponses);

		return orderResponse;
	}

	@Override
	public Order createOrder(long customerId, List<OrderItemPayload> selectedMobiles) {

		Customer customer = customerService.getCustomerDetails(customerId);

		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("pending");
		order.setCustomerId(customerId);

		List<OrderItem> orderItems = new ArrayList<>();

		double orderTotal = 0;

		for (OrderItemPayload po : selectedMobiles) {
			long mobileId = po.getMobileId();
			int qty = po.getQantity();
			Mobile mobile = mobileService.getMobileById(mobileId);
			System.out.println("Itemtotal: " + mobile.getPrice() * qty);

			OrderItem orderItem = new OrderItem();
			orderItem.setMobileId(mobileId);
			orderItem.setAmount(orderTotal);
			orderItems.add(orderItem);

			orderTotal = orderTotal + (mobile.getPrice() * qty);

			orderItem.setOrder(order);
		}
		;

		order.setOrderTotal(orderTotal);
		order.setOrderItems(orderItems);
		//saveOrder(order);
		return order;
	}

}