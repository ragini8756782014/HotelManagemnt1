package com.example.demo.service.bookingdetail;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BookingDetails;
import com.example.demo.entity.Rooms;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.BookingDetailsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.room.GetAllRoomService;

@Service
public class DeleteBookingDetailsService {

	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	@Autowired
	private GetAllRoomService getAllRoomService;

	@Autowired
	private CustomerRepository customerRepository;

	public BaseResponse deleteBookingDetails(Long id, int roomNo) {
		validate(id, roomNo);
		BaseResponse baseresponse = new BaseResponse();
		Optional<BookingDetails> dbBookingDetails = bookingDetailsRepository.findById(id);
		if (dbBookingDetails.isPresent()) {
			customerRepository.deleteByBookingId(id);
			deleteOccupancy(roomNo);
			bookingDetailsRepository.deleteById(id);
			baseresponse.setStatus(HttpStatus.ACCEPTED.value());
			baseresponse.setMessage("your BookingDetail has been successfully deleted");
			return baseresponse;
		}
		baseresponse.setStatus(HttpStatus.BAD_REQUEST.value());
		baseresponse.setMessage("your id is incorrect");
		return baseresponse;

	}

	private void deleteOccupancy(int roomNo) {
		List<Rooms> rooms = getAllRoomService.getRoom();
		rooms.stream().map(b -> {
			if (b.getAvailability() == "N") {
				if (b.getRoomnumber() == roomNo) {
					b.setAvailability("Y");
					b.setIscheckedin("N");
					b.setIsCheckedOut("Y");
				}
			}
			return b;
		});

	}

	private void validate(Long id, int roomNo) {
		if (id <= 0 || id != (Long) id) {
			throw new ValidationException("FV001", "bookingDetails id cannot be 0 or empty");
		} else if (roomNo <= 0 || roomNo != (int) roomNo) {
			throw new ValidationException("FV001", "room no cannot be 0 or empty");
		}
	}
}
