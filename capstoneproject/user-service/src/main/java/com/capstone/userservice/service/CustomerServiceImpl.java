package com.capstone.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.userservice.entity.Customer;
import com.capstone.userservice.exception.ResourceNotFoundException;
import com.capstone.userservice.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id " + id + " not found");
		}
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer) {
		// You can implement validation logic here
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer with id " + id + " not found");
		}
		customerRepository.deleteById(id);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));

		existingCustomer.setName(customer.getName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPhoneNumber(customer.getPhoneNumber());

		return customerRepository.save(existingCustomer);
	}

}
