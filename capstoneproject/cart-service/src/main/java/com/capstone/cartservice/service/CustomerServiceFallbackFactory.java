package com.capstone.cartservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.cartservice.model.Customer;

@Component
public class CustomerServiceFallbackFactory implements FallbackFactory<CustomerServiceConsumer> {

	@Override
	public CustomerServiceConsumer create(Throwable cause) {
		return new CustomerServiceConsumer() {

			@Override
			public Customer getCustomerDetails(long customerId) {
				return new Customer();
			}
		};
	}

}
