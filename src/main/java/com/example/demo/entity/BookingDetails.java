package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "booking_details")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookingid;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private String startDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private String enddate;

	private String modeofbooking;

	private String modeofpayment;

	@OneToMany(mappedBy = "bookingDetails", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JsonManagedReference
	private List<Customer> c;

//	@OneToMany(targetEntity=Rooms.class,cascade=CascadeType.ALL)
//	@JoinColumn(name= "bookingdetail_id",referencedColumnName="bookingid")
//	private List<Rooms> r;

	private String categoryType;

	private int noOfRooms;

}
