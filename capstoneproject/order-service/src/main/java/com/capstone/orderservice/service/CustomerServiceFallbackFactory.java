package com.capstone.orderservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.orderservice.model.Customer;

@Component
public class CustomerServiceFallbackFactory implements FallbackFactory<CustomerServiceConsumer> {

	@Override
	public CustomerServiceConsumer create(Throwable cause) {
		return new CustomerServiceConsumer() {

			@Override
			public Customer getCustomerDetails(long customerId) {
				System.out.println("cause: " + cause.getMessage());
				return new Customer();
			}
		};
	}

}