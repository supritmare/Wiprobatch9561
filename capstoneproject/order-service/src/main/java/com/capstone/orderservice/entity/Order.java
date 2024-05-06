package com.capstone.orderservice.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_tbl")
public class Order {

	@Id
	@Column(name = "order_id", length = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long orderId;

	@Column(name = "order_total")
	private double orderTotal;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "order_status", length = 10)
	private String orderStatus;

	@Column(name = "customer_id")
	private long customerId;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

}
