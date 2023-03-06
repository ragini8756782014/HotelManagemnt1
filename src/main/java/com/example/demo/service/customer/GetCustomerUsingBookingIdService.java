package com.example.demo.service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@Service
public class GetCustomerUsingBookingIdService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private GetAllCustomerService getAllCustomerService;

	public List<Customer> getCustomerUsingBookingId(Long id) {

		List<Customer> customer = getAllCustomerService.getAllCustomer().stream()
				.filter(e -> e.getBookingDetails().getBookingid() == id).collect(Collectors.toList());
		return customer;
	}
}
