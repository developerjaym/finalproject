package com.cooksys.dto;

public class FlightDto {
	
	private LocationDto origin;
	private LocationDto location;
	private long flightTime;
	private long offset;
	public LocationDto getOrigin() {
		return origin;
	}
	public void setOrigin(LocationDto origin) {
		this.origin = origin;
	}
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
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
