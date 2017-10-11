package com.cooksys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Flightentity;


public interface FlightRepository extends JpaRepository<Flightentity, Long> {

}
