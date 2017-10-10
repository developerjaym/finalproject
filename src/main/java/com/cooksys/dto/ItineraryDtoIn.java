package com.cooksys.dto;

import java.util.List;

public class ItineraryDtoIn {

	private List<FlightDto> flights;
	private CredentialsDto credentials;
	public List<FlightDto> getFlights() {
		return flights;
	}
	public void setFlights(List<FlightDto> flights) {
		this.flights = flights;
	}
	public CredentialsDto getCredentials() {
		return credentials;
	}
	public void setCredentials(CredentialsDto credentials) {
		this.credentials = credentials;
	}
	
}
