package com.cooksys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class FlightEntity {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "flightTime")
	private long flightTime;
	
	@Column(name = "offset")
	private long offset;
	
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
	public long getOffset() {
		return offset;
	}
	public void setOffset(long offset) {
		this.offset = offset;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightEntity other = (FlightEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}

