package com.example.demo.Controller.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.CustomerController;
import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Rooms;
import com.example.demo.service.customer.GetAllCustomerService;
import com.example.demo.service.customer.GetCustomerByIdService;
import com.example.demo.service.customer.GetCustomerUsingBookingIdService;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class CustomerServiceControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	private GetAllCustomerService getAllCustomerService;
	
	@InjectMocks
	private CustomerController customerController;
	@InjectMocks
	private GetCustomerByIdService getCustomerByIdService;
	
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void getAllCustomerTest() throws Exception {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1L,"Ragini Singh","Greate Noida",1234567890,null,13,1));
		customerList.add(new Customer(2L,"Kaju Singh","haryana",1234567890,null,44,2));

		when(getAllCustomerService.getAllCustomer()).thenReturn(customerList);
		this.mockMvc.perform(get("/customers")).andExpect(status().isOk()).andDo(print());
		
	}
	
	
	
	@Test
	public void Test() throws Exception {
		Customer customer=new Customer(1L,"Ragini Singh","Greate Noida",1234567890,null,13,1);
		
		when(getCustomerByIdService.getCustomerById(1L)).thenReturn(Optional.of(customer));

		 this.mockMvc
         .perform(get("/customer/{id}", 1L))
         .andExpect(status().isFound())
         .andExpect(MockMvcResultMatchers.jsonPath(".customerfullname").value("Ragini Singh"))
         .andExpect(MockMvcResultMatchers.jsonPath(".customeraddress").value("Greate Noida"))
         .andDo(print());
	}

}
