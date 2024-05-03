package com.capstone.mobilestore.model;

import java.util.Set;

import com.capstone.mobilestore.entity.CartItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CartResponse {

	private Long id;
	private  Customer customer;
	private Set<CartItem> items;
	
}
