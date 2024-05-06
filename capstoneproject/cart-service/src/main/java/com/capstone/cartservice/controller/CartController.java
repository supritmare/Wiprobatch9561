package com.capstone.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.cartservice.entity.Cart;
import com.capstone.cartservice.model.CartResponse;
import com.capstone.cartservice.service.CartServiceImpl;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartServiceImpl cartService;

	@GetMapping("/{cartId}")
	public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
		Cart cart = cartService.getCartById(cartId);
		return ResponseEntity.ok(cart);
	}

	@GetMapping("/{cartId}/customerdetails")
	public ResponseEntity<CartResponse> getCustomerDetailsForCart(@PathVariable("cartId") Long cartId) {
		CartResponse cartResponse = cartService.getCustomerDetailsForCart(cartId);
		return ResponseEntity.ok(cartResponse);
	}

	@PostMapping("/add/{customerId}")
	public ResponseEntity<Cart> addCartByCustomerId(@PathVariable("customerId") Long customerId) {
		Cart newCart = cartService.addCartByCustomerId(customerId);
		return ResponseEntity.ok(newCart);
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable("cartId") Long cartId) {
		cartService.deleteCart(cartId);
		return ResponseEntity.ok().build();
	}
}