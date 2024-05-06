package com.capstone.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orderitem_tbl")
public class OrderItem {

	@Id
	@Column(name = "orderitem_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long orderItemId;

	@Column(length = 2)
	private int quantity;

	@Column(name = "order_amount")
	private double amount;

	@Column(name = "mobile_id")
	private long mobileId;

	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;

}