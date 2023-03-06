package com.example.demo.classes;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingDetailsReturn {
   
	private Long bookingId;
	private List<RoomReturn> roomReturn;
	 private int totalPayment;
	
}
