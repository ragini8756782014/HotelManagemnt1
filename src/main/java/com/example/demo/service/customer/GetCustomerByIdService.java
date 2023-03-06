package com.example.demo.service.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.CustomerRepository;

@Service
public class GetCustomerByIdService {

	@Autowired
	private CustomerRepository customerRepository;

	public Optional<Customer> getCustomerById(Long id) {
		validate(id);
		Optional<Customer> dbcustomer = customerRepository.findById(id);
		if(dbcustomer.isPresent())
		{return customerRepository.findById(id);}
		else {
			throw new ValidationException("FV001", "id is incorrect or do not exist");
		}

	}

	private void validate(Long id) {
		if (id <= 0 || id != (Long) id) {
			throw new ValidationException("FV001", "customer id cannot be null or empty");
		}
	}
}
