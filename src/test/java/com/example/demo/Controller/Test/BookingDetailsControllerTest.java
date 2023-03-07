package com.example.demo.Controller.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.BookingDetailsController;
import com.example.demo.entity.BookingDetails;
import com.example.demo.entity.Customer;
import com.example.demo.service.bookingdetail.CreateBookingDetailService;
import com.example.demo.service.bookingdetail.DeleteBookingDetailsService;
import com.example.demo.service.bookingdetail.GetAllBookingDetailsService;
import com.example.demo.service.bookingdetail.GetBookingDetailByIdService;
import com.example.demo.service.bookingdetail.GetBookingDetailsUsingDateService;
import com.example.demo.service.bookingdetail.UpdateBookingDetailService;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class BookingDetailsControllerTest {
	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	private BookingDetailsController bookingDetailsController;
	@Mock
	private CreateBookingDetailService createBookingDetailService;
	@Mock
	private DeleteBookingDetailsService deleteBookingDetailsService;
	@Mock
	private GetAllBookingDetailsService getAllBookingDetailsService;
	@Mock
	private GetBookingDetailByIdService getBookingDetailByIdService;
	@Mock
	private GetBookingDetailsUsingDateService getBookingDetailsUsingDateService;
	@Mock
	private UpdateBookingDetailService updateBookingDetailService;

	private static List<Customer> customerList = new ArrayList<Customer>();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingDetailsController).build();
		customerList.add(new Customer(1L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 1));
		customerList.add(new Customer(2L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 2));

	}

	@Test
	public void getAllBookingDetailsTest() throws Exception {
		List<BookingDetails> bookingDetails = new ArrayList<>();
		bookingDetails.add(new BookingDetails(1L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online", "online",
				customerList, "Double Bed", 1));
		bookingDetails.add(new BookingDetails(2L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online", "online",
				customerList, "Single Bed", 2));
		when(getAllBookingDetailsService.getAllBookingDetails()).thenReturn(bookingDetails);
		this.mockMvc.perform(get("/bookingDetails")).andExpect(status().isOk()).andDo(print());

//		this.mockMvc.perform(get("/bookingDetails")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void updateBookingDetailTest() throws Exception {
		this.mockMvc.perform(put("/bookingDetails")).andExpect(status().isBadRequest()).andDo(print());
	}
	
	

	@Test
	public void deleteBookingDetailsTest() throws Exception {
		this.mockMvc.perform(delete("/bookingDetails")).andExpect(status().isMethodNotAllowed()).andDo(print());

	}

	@Test
	public void createBookingDetailTest() throws Exception {
		this.mockMvc.perform(post("/bookingDetails")).andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void getBookingDetailByIdTest() throws Exception {
		this.mockMvc.perform(get("/bookingDetails")).andExpect(status().isOk()).andDo(print());
	}
}
