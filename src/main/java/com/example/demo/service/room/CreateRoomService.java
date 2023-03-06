package com.example.demo.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rooms;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.RoomsRepository;
import com.example.demo.response.BaseResponse;

@Service
public class CreateRoomService {

	@Autowired
	private RoomsRepository roomsRepository;

	public BaseResponse createRoom(Rooms rooms) {

		validate(rooms);
		Rooms r = convertEntity(rooms);
		roomsRepository.save(r);
		BaseResponse baseresponse = new BaseResponse();
		baseresponse.setStatus(HttpStatus.ACCEPTED.value());
		baseresponse.setMessage("your room has been successfully registered");
		return baseresponse;
	}

	private Rooms convertEntity(Rooms rooms) {
		rooms.setAvailability("Y");
		rooms.setIscheckedin("N");
		rooms.setIsCheckedOut("N");
		rooms.setOccupancy(0);
		return rooms;
	}

	private void validate(Rooms rooms) {
		if (rooms.getCategoryType() == null || rooms.getCategoryType().isEmpty() || rooms.getCategoryType().isBlank()) {
			throw new ValidationException("FV001", "CategoryCode cannot be null or empty");
		} else if (rooms.getPriceperday() <= 0) {
			throw new ValidationException("FV002", " Priceperday cannot be 0/smaller then 0 or empty");
		} else if (rooms.getRoomnumber() <= 0) {
			throw new ValidationException("FV003", " RoomNo cannot be 0 or empty");
		} else {

		}
	}

//	private void invalidChecks() {
//		if (createRoomRequest.getCategoryCode().matches("[0-9]+")
//				|| createRoomRequest.getCategoryCode().matches("^[a-zA-Z]*$")) {
//			throw new ValidationException("FV001", "CategoryCode cannot be only character or number");
//		}
//		else if (createRoomRequest.getPriceperday().getClass().getSimpleName()!=Integer) {
//			throw new ValidationException("FV002", " Priceperday can only be integer ");
//		}]
//		else {

//	}

}
