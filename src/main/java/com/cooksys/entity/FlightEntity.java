package com.cooksys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Flightentity {

	@Id
	@GeneratedValue
	private Long id;
//	@ManyToOne
//	@JoinColumn(name="start_id")
//	private Location start;
//	@ManyToOne
//	@JoinColumn(name="destination_id")
//	private Location destination;
	private long flightTime;
	private long offset;
	
	@ManyToMany//(cascade = CascadeType.ALL)
	@JoinTable 
	private List<Itinerary> itineraries;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public Location getOrigin() {
//		return start;
//	}
//	public void setOrigin(Location origin) {
//		this.start = origin;
//	}
//	public Location getDestination() {
//		return destination;
//	}
//	public void setDestination(Location destination) {
//		this.destination = destination;
//	}
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
	public List<Itinerary> getItineraries() {
		return itineraries;
	}
	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Flightentity other = (Flightentity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
