package com.example.demo.service.room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.classes.RoomReturn;
import com.example.demo.entity.Rooms;

@Service
public class FindRoomNoService {

	@Autowired
	private GetAllRoomService getAllRoomService;

	public List<RoomReturn> findRoomNo(String category, int noOfRooms) {
		List<Rooms> availableRooms = getAllRoomService.getRoom().stream().filter(e -> "Y".equals(e.getAvailability()))
				.collect(Collectors.toList());
		List<RoomReturn> roomReturn = new ArrayList<>();
		List<Rooms> allottedRooms = createEntity(availableRooms, category, noOfRooms);
		allottedRooms.forEach(item -> {
			RoomReturn roomr = new RoomReturn();
			roomr.setRoomId(item.getRoomId());
			roomr.setRoomNo(item.getRoomnumber());
			roomr.setBillAmount(item.getPriceperday());
			roomr.setPricePerHour(item.getPricePerHours());
			roomReturn.add(roomr);
		});

		return roomReturn;

	}

	private List<Rooms> createEntity(List<Rooms> availableRooms, String c, int noOfRooms) {

		List<Rooms> rooms = new ArrayList<>();
		for (Rooms ar : availableRooms) {
			if (noOfRooms != 0) {
				if (c.equals(ar.getCategoryType())) {
					ar.setAvailability("N");
					ar.setIscheckedin("Y");
					rooms.add(ar);
					noOfRooms--;
				}
			}

		}
		return rooms;
	}
//	List<Rooms> rooms = new ArrayList<>();
//	for (Rooms ar : availableRooms) {
//		if (noOfRooms != 0) {
//			if (c.equals(ar.getCategoryType())) {
//				ar.setAvailability("N");
//				ar.setIscheckedin("Y");
//				rooms.add(ar);
//				noOfRooms--;
//			}
//		}
//		
//	}
//	return rooms;

}
