package com.example.demo.service.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Rooms;
import com.example.demo.repository.RoomsRepository;

@Service
public class GetAllRoomService {

	@Autowired
	private RoomsRepository roomsRepository;

	public List<Rooms> getRoom() {
		return roomsRepository.findAll();
	}

}
