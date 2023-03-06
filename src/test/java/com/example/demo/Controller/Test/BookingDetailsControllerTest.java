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
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.classes.RoomReturn;
import com.example.demo.controller.BookingDetailsController;
import com.example.demo.entity.BookingDetails;
import com.example.demo.entity.Category;
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

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingDetailsController).build();

	}

	@Test
	public void getAllBookingDetailsTest() throws Exception {
		this.mockMvc.perform(get("/bookingDetails")).andExpect(status().isOk()).andDo(print());
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
