package com.capstone.cartservice.model;

import java.util.Set;

import com.capstone.cartservice.entity.CartItem;

import lombok.Data;

@Data
public class CartResponse {

	private Long id;
	private Customer customer;
	private Set<CartItem> cartItems;

}