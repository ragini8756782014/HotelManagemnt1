package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.entity.Rooms;
import com.example.demo.repository.RoomsRepository;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.room.CreateRoomService;
import com.example.demo.service.room.DeleteRoomService;
import com.example.demo.service.room.GetAllRoomByIdService;
import com.example.demo.service.room.GetAllRoomService;
import com.example.demo.service.room.UpdateRoomService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
	@MockBean
	private RoomsRepository roomsRepository;
	@InjectMocks
	private CreateRoomService createRoomService;
	@InjectMocks
	private DeleteRoomService deleteRoomService;
	@InjectMocks
	private GetAllRoomByIdService getAllRoomByIdService;
	@InjectMocks
	private GetAllRoomService getAllRoomService;
	@InjectMocks
	private UpdateRoomService updateRoomService;

	@Test
	public void testCreateRoom() {
		Rooms rooms = new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400);
		BaseResponse baseResponse = createRoomService.createRoom(rooms);
		assertNotNull(baseResponse);
	}

	@Test
	public void testgetAllRoomById() {
		Rooms rooms = new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400);
		Mockito.when(roomsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(rooms));
		assertThat(getAllRoomByIdService.getRoomById(1L)).isEqualTo(Optional.of(rooms));
	}

	@Test
	public void testgetAllRoom() {
		List<Rooms> rooms = new ArrayList<>();
		rooms.add(new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400));
		rooms.add(new Rooms(2L, 1, 0, "Y", 1400, "N", "N", "Double bed",400));
		Mockito.when(roomsRepository.findAll()).thenReturn(rooms);
		assertThat(getAllRoomService.getRoom()).isEqualTo(rooms);

	}

	@Test
	public void testDeleteRoom() {
		Rooms rooms = new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400);
		BaseResponse baseResponse =deleteRoomService.deleteRoom(rooms.getRoomId());
		assertNotNull(baseResponse);
//		verify(roomsRepository, times(1)).deleteById(1L);
		
	}

	@Test
	public void testUpdateRoom() {
		Rooms rooms = new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400);
		Mockito.when(roomsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(rooms));

		BaseResponse baseResponse = updateRoomService.updateRoom(rooms);
		assertNotNull(baseResponse);

	}

	@Test
	public void testUpdateRoom2() {
		Rooms rooms = new Rooms(1L, 1, 0, "Y", 1400, "N", "N", "Double bed",400);
		BaseResponse baseResponse = updateRoomService.updateRoom(rooms);
		assertNotNull(baseResponse);
	}

}
