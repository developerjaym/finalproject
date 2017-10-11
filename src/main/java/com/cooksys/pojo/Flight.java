package com.cooksys.pojo;

public class Flight {
	
	//Name of city where flight originates
	private String origin;
	
	//Name of city where flight lands
	private String destination;
	
	//How many hours flight is in the air
	private long flightTime;
	
	//How many hours after the start of the day until the flight takes off
	private long departuretime;
	
	public String getOrigin() {
		return origin.toUpperCase();
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination.toUpperCase();
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
	public Flight(String origin, String destination, long flightTime, long departuretime) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.departuretime = departuretime;
	}
	
	

}
