package com.cooksys.dto;

public class FlightDto {
	
	private String origin;
	private String destination;
	private long flightTime;
	private long departuretime;
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getDeparturetime() {
		return departuretime;
	}
	public void setDeparturetime(long departuretime) {
		this.departuretime = departuretime;
	}
	@Override
	public String toString() {
		return "FlightDto [origin=" + origin + ", destination=" + destination + ", flightTime=" + flightTime
				+ ", departuretime=" + departuretime + "]";
	}
	
	
}
