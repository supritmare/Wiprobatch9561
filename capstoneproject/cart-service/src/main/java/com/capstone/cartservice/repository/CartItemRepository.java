package com.capstone.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capstone.cartservice.entity.CartItem;

import feign.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	@Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.mobileId = :mobileId")
	CartItem findByCartIdAndMobileId(@Param("cartId") Long cartId, @Param("mobileId") Long mobileId);

}
