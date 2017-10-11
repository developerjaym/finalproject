package com.cooksys.dto;

import java.util.List;

public class ItineraryDtoOut {
	private List<FlightDto> flights;

	public List<FlightDto> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightDto> flights) {
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "ItineraryDtoOut [flights=" + flights + "]";
	}
	
	
}
