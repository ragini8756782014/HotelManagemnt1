package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.classes.BookingDetailsReturn;
import com.example.demo.entity.BookingDetails;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.bookingdetail.CreateBookingDetailService;
import com.example.demo.service.bookingdetail.DeleteBookingDetailsService;
import com.example.demo.service.bookingdetail.GetAllBookingDetailsService;
import com.example.demo.service.bookingdetail.GetBookingDetailByIdService;
import com.example.demo.service.bookingdetail.GetBookingDetailsUsingDateService;
import com.example.demo.service.bookingdetail.UpdateBookingDetailService;

@RestController
public class BookingDetailsController {

	@Autowired
	private CreateBookingDetailService createBookingDetailService;

	@Autowired
	private GetAllBookingDetailsService getAllBookingDetailsService;

	@Autowired
	private GetBookingDetailByIdService getBookingDetailByIdService;

	@Autowired
	private DeleteBookingDetailsService deleteBookingDetailsService;

	@Autowired
	private UpdateBookingDetailService updateBookingDetailService;

	@Autowired
	private GetBookingDetailsUsingDateService getBookingDetailsBeweenDatesService;

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookingDetailsController.class);

	@PostMapping("/bookingDetails")
	public ResponseEntity<BookingDetailsReturn> createBookingDetails(@RequestBody BookingDetails bookingDetails) {
		logger.info("saving booking details ");
		return new ResponseEntity<>(createBookingDetailService.createBookingDetail(bookingDetails), HttpStatus.CREATED);
	}

	@GetMapping("/bookingDetails")
	public ResponseEntity<List<BookingDetails>> getAllBookingDetails() {
		logger.info("getting list of all bookingDetails");
		return new ResponseEntity<>(getAllBookingDetailsService.getAllBookingDetails(), HttpStatus.OK);
	}

	@GetMapping("/bookingDetails/{id}")
	public ResponseEntity<Optional<BookingDetails>> getBookingDetailsById(@PathVariable("id") Long id) {
		logger.info("getting booking detail using id");
		return new ResponseEntity<>(getBookingDetailByIdService.getBookingDetailsById(id), HttpStatus.OK);
	}

	@DeleteMapping("/bookingDetails/{id}/{roomNo}")
	public ResponseEntity<BaseResponse> deleteBookingDetails(@PathVariable("id") Long id,
			@PathVariable("roomNo") int roomNo) {
		logger.info("deleteing bookingdetails using booking id");
		return new ResponseEntity<>(deleteBookingDetailsService.deleteBookingDetails(id, roomNo), HttpStatus.FOUND);
	}

//	when you have to change room then use this to update your booking 
	@PutMapping("/bookingDetails")
	public ResponseEntity<BookingDetailsReturn> updateBookingDetails(@RequestBody BookingDetails bookingDetails) {
		logger.info("updaing all details of bookingdetail using put mapping ");
		return new ResponseEntity<>(updateBookingDetailService.updateBookingDetails(bookingDetails), HttpStatus.OK);
	}

//	when you want to update customer and other data in bookingdetails then use this method
	@PatchMapping("/bookingDetails")
	public ResponseEntity<BaseResponse> updateBookingDetailsUsingPatch(@PathVariable("id") Long id,
			@RequestBody Map<String, Object> fields) {
		logger.info("updating few details of booking Details using patch mapping ");
		return new ResponseEntity<>(updateBookingDetailService.updateBookingDetailsUsingPatch(id, fields),
				HttpStatus.OK);
	}

	@GetMapping("/bookingDetailsbetween/{startDate}/{endDate}")
	public ResponseEntity<List<BookingDetails>> getBookingDetailsBetweenDates(
			@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
		logger.info("getting bookingDetails between two dates ");
		return new ResponseEntity<>(getBookingDetailsBeweenDatesService.getBookingDetailsBetween(startDate, endDate),
				HttpStatus.OK);
	}

	@GetMapping("/bookingDetailsbefore/{startDate}")
	public ResponseEntity<List<BookingDetails>> getBookingDetailsBeforeDate(
			@PathVariable("startDate") String startDate) {
		logger.info("getting bookingDetails of before a given date ");
		return new ResponseEntity<>(getBookingDetailsBeweenDatesService.getBookingDetailsBeforeDate(startDate),
				HttpStatus.OK);
	}

	@GetMapping("/bookingDetailsyear/{startDate}")
	public ResponseEntity<List<BookingDetails>> getBookingDetailsUsingYear(@PathVariable("startDate") String startDate)
			throws ParseException {
		logger.info("getting booking details using year");
		return new ResponseEntity<>(getBookingDetailsBeweenDatesService.getBookingDetailsUsingYear(startDate),
				HttpStatus.OK);
	}

}
