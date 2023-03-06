package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.classes.BookingDetailsReturn;
import com.example.demo.classes.RoomReturn;
import com.example.demo.entity.BookingDetails;
import com.example.demo.entity.Customer;
import com.example.demo.repository.BookingDetailsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.bookingdetail.CreateBookingDetailService;
import com.example.demo.service.bookingdetail.DeleteBookingDetailsService;
import com.example.demo.service.bookingdetail.GetAllBookingDetailsService;
import com.example.demo.service.bookingdetail.GetBookingDetailByIdService;
import com.example.demo.service.bookingdetail.UpdateBookingDetailService;
import com.example.demo.service.customer.CreateCustomerService;
import com.example.demo.service.customer.GetAllCustomerService;
import com.example.demo.service.customer.UpdateCustomerService;
import com.example.demo.service.room.FindRoomNoService;
import com.example.demo.service.room.GetAllRoomService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class BookingDetailsServiceTest {
	@MockBean
	private BookingDetailsRepository bookingDetailsRepository;
	@InjectMocks
	private CreateBookingDetailService createBookingDetailService;
	@InjectMocks
	private DeleteBookingDetailsService deleteBookingDetailsService;
	@InjectMocks
	private GetAllBookingDetailsService getAllBookingDetailsService;;
	@InjectMocks
	private GetBookingDetailByIdService getBookingDetailByIdService;
	@InjectMocks
	private UpdateBookingDetailService updateBookingDetailService;
	@MockBean
	private FindRoomNoService findRoomNoService;
	@MockBean
	private CreateCustomerService createCustomerService;
	@MockBean
	private CustomerRepository customerRepository;
	@MockBean
	private GetAllCustomerService getAllCustomerService;
	@MockBean
	private GetAllRoomService getAllRoomService;
	@MockBean
	private UpdateCustomerService updateCustomerService;

	private static List<Customer> customerList = new ArrayList<Customer>();

	@BeforeEach
	public void setUp() {

		customerList.add(new Customer(1L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 1));
		customerList.add(new Customer(2L, "Ragini Singh", "Greate Noida", 1234567890, null, 13, 2));

	}

	@Test
	public void createBookingDetailTest() {
		BookingDetails bookingDetails = new BookingDetails(1L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online",
				"online", customerList, "Double Bed", 2);
		RoomReturn roomReturn = new RoomReturn(1L, 1, 1400, 400);
		when(findRoomNoService.findRoomNo(Mockito.anyString(), Mockito.anyInt())).thenReturn(List.of(roomReturn));
		BookingDetailsReturn bookingDetailsReturn = createBookingDetailService.createBookingDetail(bookingDetails);
		assertNotNull(bookingDetailsReturn);
	}

	@Test
	public void deleteBookingDetails() {
		BookingDetails bookingDetails = new BookingDetails(1L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online",
				"online", customerList, "Double Bed", 2);
//    	BaseResponse baseResponse = new BaseResponse("200","deleted");
		when(bookingDetailsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bookingDetails));
		BaseResponse baseResponse = deleteBookingDetailsService.deleteBookingDetails(1L, 1);
		assertNotNull(baseResponse);
	}

	@Test
	public void UpdateBookingDetailServiceTest() {
		BookingDetails bookingDetails = new BookingDetails(1L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online",
				"online", customerList, "Double Bed", 2);
		RoomReturn roomReturn = new RoomReturn(1L, 1, 1400, 400);
		when(findRoomNoService.findRoomNo(Mockito.anyString(), Mockito.anyInt())).thenReturn(List.of(roomReturn));
		BookingDetailsReturn bookingDetailsReturn = updateBookingDetailService.updateBookingDetails(bookingDetails);
		assertNotNull(bookingDetailsReturn);

	}

	@Test
	public void getAllBookingDetails() {
		List<BookingDetails> listbd = new ArrayList<>();
		listbd.add(new BookingDetails(1L, "02-03-2023 00:00:00", "05-03-2023 04:20:13", "online", "online",
				customerList, "Double Bed", 2));
		listbd.add(new BookingDetails(2L, "04-03-2023 00:00:00", "08-03-2023 04:20:13", "online", "online",
				customerList, "Single Bed", 2));
		Mockito.when(bookingDetailsRepository.findAll()).thenReturn(listbd);
		assertThat(getAllBookingDetailsService.getAllBookingDetails()).isEqualTo(listbd);
	}

	@Test
	public void getBookingDetailById() {
		BookingDetails bookingDetails = new BookingDetails(1L, "2-03-2023 00:00:00", "5-03-2023 04:20:13", "online",
				"online", customerList, "Double Bed", 2);

		Mockito.when(bookingDetailsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(bookingDetails));
		assertThat(getBookingDetailByIdService.getBookingDetailsById(1L)).isEqualTo(Optional.of(bookingDetails));
	}

}
