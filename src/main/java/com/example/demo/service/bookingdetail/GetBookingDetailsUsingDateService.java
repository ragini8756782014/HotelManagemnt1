package com.example.demo.service.bookingdetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BookingDetails;
import com.example.demo.repository.BookingDetailsRepository;

@Service
public class GetBookingDetailsUsingDateService {

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	private GetAllBookingDetailsService getAllBookingDetailsService;

	public List<BookingDetails> getBookingDetailsBetween(String startDate, String EndDate) {
		return bookingDetailsRepository.findAllByStartDateBetween(startDate, EndDate);
	}

	public List<BookingDetails> getBookingDetailsBeforeDate(String date) {
		return bookingDetailsRepository.findAllByStartDateBefore(date);
	}

	public List<BookingDetails> getBookingDetailsUsingYear(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(date);
		Date currentDate = format.parse(date);
		System.out.println(currentDate);
		int year = currentDate.getYear();
		List<BookingDetails> list = getAllBookingDetailsService.getAllBookingDetails().stream().filter(e -> {

			Date date_to_check;
			try {
				date_to_check = format.parse(e.getStartDate());
				return date_to_check.getYear() == year;
			} catch (ParseException e1) {

				e1.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());

		return list;
	}
}
