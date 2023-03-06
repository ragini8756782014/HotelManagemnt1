package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.customer.GetAllCustomerService;
import com.example.demo.service.customer.GetCustomerByIdService;
import com.example.demo.service.customer.GetCustomerUsingBookingIdService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
	@MockBean
	private CustomerRepository customerRepository;
	@InjectMocks
	private GetCustomerUsingBookingIdService getCustomerUsingBookingIdService;
	@InjectMocks
	private GetAllCustomerService getAllCustomerService;
	@InjectMocks
	private GetCustomerByIdService getCustomerByIdService;

	@Test
	public void getAllCustomerServiceTest() {
		List<Customer> customerl = new ArrayList<>();
		customerl.add(new Customer(1L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 2));
		customerl.add(new Customer(2L, "Kaju", "haryana", 1234567890, null, 56, 2));
		Mockito.when(customerRepository.findAll()).thenReturn(customerl);
		assertThat(getAllCustomerService.getAllCustomer()).isEqualTo(customerl);
	}
	
	
	public void  getCustomerByIdTest()
	{     Customer customer= new Customer(1L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 2);
		Mockito.when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
		assertThat(getCustomerByIdService.getCustomerById(1L)).isEqualTo(Optional.of(customer));
	}
	
}
