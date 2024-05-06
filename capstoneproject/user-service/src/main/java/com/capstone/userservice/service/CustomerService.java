package com.capstone.userservice.service;

import java.util.List;
import java.util.Optional;

import com.capstone.userservice.entity.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Optional<Customer> getCustomerById(Long id);

	Customer addCustomer(Customer customer);

	void deleteCustomer(Long id);

	Customer updateCustomer(Long id, Customer customer);
}
