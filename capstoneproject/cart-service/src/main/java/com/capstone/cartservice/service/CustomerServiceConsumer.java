package com.capstone.cartservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.cartservice.model.Customer;

@FeignClient(name = "USER-SERVICE")
public interface CustomerServiceConsumer {

	@GetMapping("/customers/{id}")
	Customer getCustomerDetails(@PathVariable("id") long customerId);
}
