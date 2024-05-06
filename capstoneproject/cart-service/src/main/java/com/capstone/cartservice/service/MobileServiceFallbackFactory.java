package com.capstone.cartservice.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.capstone.cartservice.model.Mobile;

@Component
public class MobileServiceFallbackFactory implements FallbackFactory<MobileServiceConsumer> {

	@Override
	public MobileServiceConsumer create(Throwable cause) {
		return new MobileServiceConsumer() {

			@Override
			public Mobile getMobileDetails(long mobileId) {
				return new Mobile();
			}

		};
	}

}