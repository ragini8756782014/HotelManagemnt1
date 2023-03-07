package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Customer;
import com.example.demo.service.category.GetCategoryByIdService;
import com.example.demo.service.customer.GetAllCustomerService;
import com.example.demo.service.customer.GetCustomerByIdService;
import com.example.demo.service.customer.GetCustomerUsingBookingIdService;

@RestController
public class CustomerController {

	@Autowired
	private GetAllCustomerService getAllCustomerService;
	@Autowired
	private GetCustomerUsingBookingIdService getCustomerUsingBookingIdService;
	@Autowired
	private GetCustomerByIdService getCustomerByIdService;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookingDetailsController.class);

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		logger.info("getting all customer details ");
		return new ResponseEntity<>(getAllCustomerService.getAllCustomer(), HttpStatus.OK);
	}

	@GetMapping("/customersUsingBookingId/{id}")
	public ResponseEntity<List<Customer>> findCustomerUsingBookingId(@PathVariable("id") Long id) {
		logger.info("getting customer details using booking id");
		return new ResponseEntity<>(getCustomerUsingBookingIdService.getCustomerUsingBookingId(id), HttpStatus.OK);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Optional<Customer>> findCustomerById(@PathVariable("id") Long id) {
		logger.info("getting customer details using customer id");
		return new ResponseEntity<>(getCustomerByIdService.getCustomerById(id), HttpStatus.OK);
	}
}
