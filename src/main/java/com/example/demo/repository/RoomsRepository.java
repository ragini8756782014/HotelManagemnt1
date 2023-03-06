package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Rooms;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {

}
