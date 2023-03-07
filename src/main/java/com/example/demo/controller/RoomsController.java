package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Rooms;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.room.CreateRoomService;
import com.example.demo.service.room.DeleteRoomService;
import com.example.demo.service.room.GetAllRoomByIdService;
import com.example.demo.service.room.GetAllRoomService;
import com.example.demo.service.room.UpdateRoomService;

@RestController
public class RoomsController {

	@Autowired
	private CreateRoomService createRoomService;

	@Autowired
	private DeleteRoomService deleteRoomService;

	@Autowired
	private GetAllRoomByIdService getAllRoomByIdService;

	@Autowired
	private GetAllRoomService getAllRoomService;

	@Autowired
	private UpdateRoomService updateRoomService;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BookingDetailsController.class);

	@PostMapping("/room")
	public ResponseEntity<BaseResponse> createRoom(@RequestBody Rooms rooms) {
		logger.info("creating new rooms ");
		return  new ResponseEntity<>(createRoomService.createRoom(rooms), HttpStatus.CREATED);
	}

	@GetMapping("/room")
	public ResponseEntity<List<Rooms>> getRoom() {
		logger.info("getting all rooms");
		return new ResponseEntity<>(getAllRoomService.getRoom(), HttpStatus.OK);
	}

	@GetMapping("/room/{id}")
	public ResponseEntity<Optional<Rooms>> getRoomById(@PathVariable("id") Long id) {
		logger.info("getting rooms using room id ");
		return new ResponseEntity<>(getAllRoomByIdService.getRoomById(id), HttpStatus.FOUND);
	}

	@DeleteMapping("/room/{id}")
	public ResponseEntity<BaseResponse> deleteRoom(@PathVariable("id") Long id) {
		logger.info("deleting room using room id");
		return new ResponseEntity<>(deleteRoomService.deleteRoom(id), HttpStatus.OK);
	}

	@PutMapping("/room")
	public ResponseEntity<BaseResponse> updateRooms(@RequestBody Rooms rooms) {
		logger.info("updating all details of room ");
		return new ResponseEntity<>(updateRoomService.updateRoom(rooms), HttpStatus.OK);
	}
}
