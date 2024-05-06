package com.capstone.cartservice.payload;

import java.util.ArrayList;
import java.util.List;

public class CartPayload {

	private Long customerId;
	private List<CartItemPayload> cartItems = new ArrayList<>();

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<CartItemPayload> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemPayload> cartItems) {
		this.cartItems = cartItems;
	}

}
