package com.capstone.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.orderservice.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}