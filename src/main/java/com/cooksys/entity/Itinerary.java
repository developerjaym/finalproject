package com.cooksys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cooksys.pojo.Flight;

@Entity
@Table(name = "Location")
public class Itinerary {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "flights")
	private List<FlightEntity> flights;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<FlightEntity> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightEntity> flights) {
		this.flights = flights;
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
		Itinerary other = (Itinerary) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
