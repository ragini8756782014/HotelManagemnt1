package com.example.demo.service.bookingdetail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BookingDetails;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.BookingDetailsRepository;


@Service
public class GetBookingDetailByIdService {

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	public Optional<BookingDetails> getBookingDetailsById(Long id) {
		validate(id);
		Optional<BookingDetails> dbBookingDetails = bookingDetailsRepository.findById(id);
		if(dbBookingDetails.isPresent())
		  {return bookingDetailsRepository.findById(id);}
		else {
			throw new ValidationException("FV001", "tgis booking id do not exist");
		}

	}

	private void validate(Long id) {
		if (id <= 0 || id != (Long) id) {
			throw new ValidationException("FV001", "bookingDetails id cannot be 0 or empty");
		}
		
	}
	
}
