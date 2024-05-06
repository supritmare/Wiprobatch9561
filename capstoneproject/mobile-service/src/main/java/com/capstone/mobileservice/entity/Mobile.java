package com.capstone.mobileservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mobile_tbl")
public class Mobile {

	@Id
	@Column(name = "mobile_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "mobile_model")
	@NotBlank(message = "model is required")
	private String model;

	@Column(name = "mobile_brand")
	@NotBlank(message = "brand is required")
	private String brand;

	@Column(name = "mobile_price")
	private double price;

}
