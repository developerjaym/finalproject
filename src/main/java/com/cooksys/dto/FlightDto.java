package com.cooksys.dto;

public class FlightDto {
	
	private LocationDto origin;
	private LocationDto destination;
	private long flightTime;
	private long offset;
	public LocationDto getOrigin() {
		return origin;
	}
	public void setOrigin(LocationDto origin) {
		this.origin = origin;
	}
	
	public LocationDto getDestination() {
		return destination;
	}
	public void setDestination(LocationDto destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	
}
