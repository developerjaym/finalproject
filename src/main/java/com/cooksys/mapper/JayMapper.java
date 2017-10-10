package com.cooksys.mapper;

import java.util.ArrayList;
import java.util.List;

import com.cooksys.dto.CredentialsDto;
import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.dto.LocationDto;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.Customer;
import com.cooksys.entity.Flightentity;
import com.cooksys.entity.Itinerary;
import com.cooksys.entity.Location;

public class JayMapper
{
//
//	
//	public static Credentials toCredentials(CredentialsDto credentialsDto)
//	{
//		Credentials credentials = new Credentials();
//		credentials.setPassword(credentialsDto.getPassword());
//		credentials.setUsername(credentialsDto.getUsername());
//		return credentials;
//	}
//	
//	public static Itinerary toItinerary(ItineraryDtoIn itineraryDtoIn, Customer customer)
//	{
//		Itinerary itinerary = new Itinerary();
//		itinerary.setCustomer(customer);
//		itinerary.setFlights(toFlights(itineraryDtoIn.getFlights()));
//		
//		return itinerary;
//	}
//	
//	private static List<Flightentity> toFlights(List<FlightDto> flightDtos) 
//	{
//		List<Flightentity> flights = new ArrayList<Flightentity>();
//		flightDtos.forEach((flightDto)->{
//			flights.add(toFlight(flightDto));
//		});
//		return flights;
//	}
//
//	public static Flightentity toFlight(FlightDto flightDto)
//	{
//		Flightentity flight = new Flightentity();
//		flight.setFlightTime(flightDto.getFlightTime());
//		flight.setOffset(flightDto.getOffset());
//		flight.setDestination(toLocation(flightDto.getDestination()));
//		flight.setOrigin(toLocation(flightDto.getOrigin()));
//		
//		return flight;
//	}
//
//	public static Location toLocation(LocationDto locationDto) 
//	{
//		Location location = new Location();
//		location.setCity(locationDto.getCity());
//		location.setLatitude(locationDto.getLatitude());
//		location.setLongitude(locationDto.getLongitude());
//		
//		return location;
//	}
//
//	public static List<ItineraryDtoOut> toItineraryDtoOuts(List<Itinerary> itineraries) 
//	{
//		List<ItineraryDtoOut> itineraryDtoOuts = new ArrayList<>();
//		itineraries.forEach((itinerary)->{
//			itineraryDtoOuts.add(toItineraryDtoOut(itinerary));
//		});
//		
//		return itineraryDtoOuts;
//	}
//
//	private static ItineraryDtoOut toItineraryDtoOut(Itinerary itinerary) 
//	{
//		ItineraryDtoOut itineraryDtoOut = new ItineraryDtoOut();
//		itineraryDtoOut.setFlights(toFlightDtos(itinerary.getFlights()));
//		
//		return itineraryDtoOut;
//	}
//
//	private static List<FlightDto> toFlightDtos(List<Flightentity> flights) 
//	{
//		// TODO
//		return null;
//	}
//
//	private static FlightDto toFlightDto(Flightentity flight)
//	{
//		FlightDto flightDto = new FlightDto();
//		//TODO
//		return null;
//	}
//	
//	public static ItineraryDtoOut toItineraryDtoOut(ItineraryDtoIn itineraryDtoIn) 
//	{
//		ItineraryDtoOut itineraryDtoOut = new ItineraryDtoOut();
//		itineraryDtoOut.setFlights(itineraryDtoIn.getFlights());
//		
//		return itineraryDtoOut;
//	}
}
