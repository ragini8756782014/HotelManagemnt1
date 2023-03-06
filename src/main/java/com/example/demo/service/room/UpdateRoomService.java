package com.example.demo.service.room;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rooms;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.RoomsRepository;
import com.example.demo.response.BaseResponse;

@Service
public class UpdateRoomService {

	@Autowired
	private RoomsRepository roomsRepository;

	public BaseResponse updateRoom(Rooms rooms) {

//		validate(rooms);
//		invalidChecks();
		BaseResponse baseresponse = new BaseResponse();
		Optional<Rooms> room = roomsRepository.findById(rooms.getRoomId());
		if (room.isPresent()) {
			Rooms r= convertEntity( rooms);
			roomsRepository.save(r);
		} else {
			baseresponse.setStatus(HttpStatus.BAD_REQUEST.value());
			baseresponse.setMessage("your room id is do not exist in database");
			return baseresponse;
		}
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

//	private void validate(Rooms rooms) {
//		if (rooms.getCategoryType() == null || rooms.getCategoryType().isEmpty() || rooms.getCategoryType().isBlank()) {
//			throw new ValidationException("FV001", "CategoryCode cannot be null or empty");
//		} else if (rooms.getPriceperday() <= 0) {
//			throw new ValidationException("FV002", " Priceperday cannot be 0/smaller then 0 or empty");
//		} else if (rooms.getRoomnumber() <= 0) {
//			throw new ValidationException("FV003", " RoomNo cannot be 0 or empty");
//		} else if (rooms.getRoomId() <= 0) {
//			throw new ValidationException("FV004", " roomsId cannot be 0 or empty");
//		} else {
//
//		}
//	}


}
