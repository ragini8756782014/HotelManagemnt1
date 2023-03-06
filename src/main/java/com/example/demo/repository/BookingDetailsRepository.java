package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BookingDetails;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
	
	List<BookingDetails> findAllByStartDateBetween(String startDate, String enddate);
	
	List<BookingDetails> findAllByStartDateBefore(String startDate);
	
	
}
