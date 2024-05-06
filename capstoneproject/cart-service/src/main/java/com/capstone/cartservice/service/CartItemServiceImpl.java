package com.capstone.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.entity.CartItem;
import com.capstone.cartservice.exception.ResourceNotFoundException;
import com.capstone.cartservice.model.CartItemResponse;
import com.capstone.cartservice.model.Mobile;
import com.capstone.cartservice.repository.CartItemRepository;
import com.capstone.cartservice.repository.CartRepository;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private MobileServiceConsumer mobileServiceConsumer;

	@Override
	public CartItem addOrUpdateItem(Long cartId, Long mobileId, int quantity) throws ResourceNotFoundException {
		Mobile mobile = mobileServiceConsumer.getMobileDetails(mobileId);

		if (mobile == null) {
			throw new ResourceNotFoundException("Mobile not found with id: " + mobileId);
		}

		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));

		CartItem cartItem = cartItemRepository.findByCartIdAndMobileId(cartId, mobileId);
		if (cartItem == null) {
			cartItem = new CartItem();
			cartItem.setId(cartId);
			cartItem.setMobileId(mobileId);
			cartItem.setCart(cart);
		}

		cartItem.setQuantity(quantity);

		double itemTotal = calculateItemTotal(mobile.getPrice(), quantity);
		cartItem.setItemTotal(itemTotal);

		return cartItemRepository.save(cartItem);
	}

	private double calculateItemTotal(double price, int quantity) {
		return price * quantity;
	}

	@Override
	public void removeItem(Long itemId) throws ResourceNotFoundException {
		CartItem cartItem = getItemById(itemId);
		cartItemRepository.delete(cartItem);

	}

	@Override
	public CartItem updateQuantity(Long itemId, int quantity) throws ResourceNotFoundException {
		CartItem cartItem = cartItemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + itemId));
		cartItem.setQuantity(quantity);
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem getItemById(Long itemId) throws ResourceNotFoundException {
		return cartItemRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem not found with id: " + itemId));
	}

	@Override
	public CartItemResponse getMobileDetailsForCartItem(Long cartItemId) {
		Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
		if (optionalCartItem.isEmpty()) {
			throw new ResourceNotFoundException("CartItem not found with Id : " + cartItemId);
		}
		CartItem cartItem = optionalCartItem.get();

		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setId(cartItem.getId());
		cartItemResponse.setQuantity(cartItem.getQuantity());
		cartItemResponse.setItemTotal(cartItem.getItemTotal());
		cartItemResponse.setCart(cartItem.getCart());

		Mobile mobile = mobileServiceConsumer.getMobileDetails(cartItem.getMobileId());
		cartItemResponse.setMobile(mobile);

		return cartItemResponse;
	}

}