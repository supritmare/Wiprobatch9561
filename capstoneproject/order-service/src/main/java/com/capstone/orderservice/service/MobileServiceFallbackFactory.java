package com.capstone.orderservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.orderservice.model.Mobile;

@Component
public class MobileServiceFallbackFactory implements FallbackFactory<MobileServiceConsumer> {

	@Override
	public MobileServiceConsumer create(Throwable cause) {
		return new MobileServiceConsumer() {

			@Override
			public Mobile getMobileById(long mobileId) {
				System.out.println("cause: " + cause.getMessage());
				return new Mobile();
			}

		};
	}

}