package com.capstone.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.exception.ResourceNotFoundException;
import com.capstone.cartservice.model.CartResponse;
import com.capstone.cartservice.model.Customer;
import com.capstone.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CustomerServiceConsumer customerServiceConsumer;

	@Override
	public Cart getCartById(Long cartId) {
		return cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found with id: " + cartId));
	}

	@Override
	public CartResponse getCustomerDetailsForCart(Long cartId) {
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if (optionalCart.isEmpty()) {
			throw new ResourceNotFoundException("Cart not found with Id : " + cartId);
		}
		Cart cart = optionalCart.get();

		CartResponse cartResponse = new CartResponse();
		cartResponse.setId(cart.getCartId());
		cartResponse.setCartItems(cart.getItems());
		

		Customer customer = customerServiceConsumer.getCustomerDetails(cart.getCustomerId());
		cartResponse.setCustomer(customer);

		return cartResponse;

	}

	@Override
	public Cart addCartByCustomerId(Long customerId) {
		Customer customer = customerServiceConsumer.getCustomerDetails(customerId);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer not found with id: " + customerId);
		}

		Cart newCart = new Cart();
		newCart.setCustomerId(customerId);
		return cartRepository.save(newCart);
	}

	@Override
	public void deleteCart(Long cartId) {
		Cart cart = getCartById(cartId);
		cartRepository.delete(cart);

	}

}