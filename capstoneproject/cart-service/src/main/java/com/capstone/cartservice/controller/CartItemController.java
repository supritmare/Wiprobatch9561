package com.capstone.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.cartservice.entity.CartItem;
import com.capstone.cartservice.model.CartItemResponse;
import com.capstone.cartservice.service.CartItemServiceImpl;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

	@Autowired
	private CartItemServiceImpl cartItemServiceImpl;

	@PostMapping("/addOrUpdate")
	public ResponseEntity<CartItem> addOrUpdateItem(@RequestParam Long cartId, @RequestParam Long mobileId,
			@RequestParam int quantity) {
		CartItem cartItem = cartItemServiceImpl.addOrUpdateItem(cartId, mobileId, quantity);
		if (cartItem == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartItem);
	}

	@PutMapping("/updateqty/{Id}")
	public ResponseEntity<CartItem> updateQuantity(@PathVariable Long itemId, @RequestParam int quantity) {
		CartItem cartItem = cartItemServiceImpl.updateQuantity(itemId, quantity);
		if (cartItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	}

	@GetMapping("/{Id}")
	public ResponseEntity<CartItem> getItemById(@PathVariable Long itemId) {
		CartItem cartItem = cartItemServiceImpl.getItemById(itemId);
		if (cartItem == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cartItem, HttpStatus.OK);
	}

	@DeleteMapping("/{Id}")
	public ResponseEntity<Void> removeItem(@PathVariable("itemId") Long itemId) {
		cartItemServiceImpl.removeItem(itemId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{cartItemId}/mobile")
	public ResponseEntity<CartItemResponse> getMobileDetailsForCartItem(@PathVariable("cartItemId") Long cartItemId) {
		CartItemResponse cartItemResponse = cartItemServiceImpl.getMobileDetailsForCartItem(cartItemId);
		return ResponseEntity.ok(cartItemResponse);
	}

}
