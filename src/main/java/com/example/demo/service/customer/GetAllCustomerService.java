package com.example.demo.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class GetAllCustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

}
