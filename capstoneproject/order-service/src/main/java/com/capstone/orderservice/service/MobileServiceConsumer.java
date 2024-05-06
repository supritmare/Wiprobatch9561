package com.capstone.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.orderservice.model.Mobile;

@FeignClient(name = "MOBILE-SERVICE", fallbackFactory = MobileServiceFallbackFactory.class)
public interface MobileServiceConsumer {

	@GetMapping("/mobile/{id}")
	Mobile getMobileById(@PathVariable("id") long mobileId);

}