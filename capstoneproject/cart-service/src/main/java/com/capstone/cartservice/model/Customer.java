package com.capstone.cartservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Customer{

	Long id;
	String userName;
	String password;
	String name;
	String email;
	String phoneNumber;

}
