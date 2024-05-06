package com.capstone.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
