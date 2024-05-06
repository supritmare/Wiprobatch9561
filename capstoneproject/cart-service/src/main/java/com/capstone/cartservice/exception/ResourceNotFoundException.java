package com.capstone.cartservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Item not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
