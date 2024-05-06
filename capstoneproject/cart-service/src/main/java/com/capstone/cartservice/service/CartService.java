package com.capstone.cartservice.service;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.model.CartResponse;

public interface CartService {
	
	Cart getCartById(Long cartId);

	CartResponse getCustomerDetailsForCart(Long cartId);

	Cart addCartByCustomerId(Long customerId);

	void deleteCart(Long cartId);
}
