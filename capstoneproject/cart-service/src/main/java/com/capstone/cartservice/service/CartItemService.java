package com.capstone.cartservice.service;

import com.capstone.cartservice.entity.CartItem;
import com.capstone.cartservice.exception.ResourceNotFoundException;
import com.capstone.cartservice.model.CartItemResponse;

public interface CartItemService {

	CartItem addOrUpdateItem(Long cartId, Long mobileId, int quantity) throws ResourceNotFoundException;

	void removeItem(Long itemId) throws ResourceNotFoundException;

	CartItemResponse getMobileDetailsForCartItem(Long cartItemId);

	CartItem updateQuantity(Long itemId, int quantity) throws ResourceNotFoundException;

	CartItem getItemById(Long itemId) throws ResourceNotFoundException;
}
