package com.capstone.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.cartservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
