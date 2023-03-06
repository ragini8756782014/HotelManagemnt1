package com.example.demo.Controller.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.RoomsController;
import com.example.demo.entity.Rooms;
import com.example.demo.response.BaseResponse;
import com.example.demo.service.room.CreateRoomService;
import com.example.demo.service.room.DeleteRoomService;
import com.example.demo.service.room.GetAllRoomByIdService;
import com.example.demo.service.room.GetAllRoomService;
import com.example.demo.service.room.UpdateRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class RoomsControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Mock
	private CreateRoomService createRoomService;
	@Mock
	private DeleteRoomService deleteRoomService;
	@Mock
	private GetAllRoomByIdService getAllRoomByIdService;
	@Mock
	private GetAllRoomService getAllRoomService;
	@Mock
	private UpdateRoomService updateRoomService;

	@InjectMocks
	private RoomsController roomsController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(roomsController).build();
	}

	@Test	
	public void getAllRoomTest() throws Exception {
		List<Rooms> expectedResult = new ArrayList<>();
		expectedResult.add(new Rooms(1L,1,0,"Y",1400,"N","N","Double bed",400));
		expectedResult.add(new Rooms(2L,1,0,"Y",1400,"N","N","Double bed",400));

		when(getAllRoomService.getRoom()).thenReturn(expectedResult);
		this.mockMvc.perform(get("/room")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getAllRoomByIdTest() throws Exception {
		Rooms rooms = new Rooms(1L,1,0,"Y",1400,"N","N","Double Bed",400);
		when(getAllRoomByIdService.getRoomById(1L)).thenReturn(Optional.of(rooms));

		 this.mockMvc
         .perform(get("/room/{id}", 1L))
         .andExpect(status().isFound())
         .andExpect(MockMvcResultMatchers.jsonPath(".categoryType").value("Double Bed"))
         .andExpect(MockMvcResultMatchers.jsonPath(".availability").value("Y"))
         .andExpect(MockMvcResultMatchers.jsonPath(".ischeckedin").value("N"))
         .andDo(print());
	}

	@Test
    public void createRoomTest() throws Exception {
		Rooms rooms = new Rooms(1L,1,0,"Y",1400,"N","N","Double bed",400);
		BaseResponse baseResponse =new BaseResponse();
		baseResponse.setMessage("your room has been successfully registered");
		baseResponse.setStatus(202);
        when(createRoomService.createRoom(Mockito.any())).thenReturn(baseResponse);
        //Converting our Java object to JSON format because MockMvc works only with JSON format
        ObjectMapper mapper = new ObjectMapper();
        String jsonbody = mapper.writeValueAsString(rooms);
        System.out.println(jsonbody);
        this.mockMvc
                .perform(
                        post("/room")
                                .content(jsonbody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(".status").value(202))
                .andExpect(MockMvcResultMatchers.jsonPath(".message").value("your room has been successfully registered"))
     
                .andDo(print());
    }

	@Test
    public void deleteRoomTest() throws Exception {
		Rooms rooms = new Rooms(1L,1,0,"Y",1400,"N","N","Double bed",400);
		BaseResponse baseResponse =new BaseResponse();
		baseResponse.setMessage("your room has been successfully deleted");
		baseResponse.setStatus(202);
        when(deleteRoomService.deleteRoom(Mockito.anyLong())).thenReturn(baseResponse);
        mockMvc.perform(delete("/room/{id}", rooms.getRoomId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".status").value(202))
                .andExpect(MockMvcResultMatchers.jsonPath(".message").value("your room has been successfully deleted"))
                .andDo(print());
    }
	
	
	
}
