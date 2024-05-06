package com.capstone.cartservice.model;

import com.capstone.cartservice.entity.Cart;

import lombok.Data;

@Data
public class CartItemResponse {

	private Long id;
	private int quantity;
	private Mobile mobile;
	private Cart cart;
	private double itemTotal;

}
