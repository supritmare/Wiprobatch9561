package com.capstone.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.userservice.entity.Customer;
import com.capstone.userservice.exception.ResourceNotFoundException;
import com.capstone.userservice.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
		Customer customer = customerService.getCustomerById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		Customer updatedCustomer = customerService.updateCustomer(id, customer);
		return ResponseEntity.ok().body(updatedCustomer);
	}

}