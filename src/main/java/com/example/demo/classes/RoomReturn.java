package com.example.demo.classes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomReturn implements Serializable {
  
	private static final long serialVersionUID = 1L;

	private Long roomId;
	
	private  int roomNo ;
	
	private int billAmount;
	
	private int pricePerHour;
}
