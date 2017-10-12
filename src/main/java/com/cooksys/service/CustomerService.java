package com.cooksys.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.CredentialsDto;
import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.Customer;
import com.cooksys.entity.Flightentity;
import com.cooksys.entity.Itinerary;
import com.cooksys.mapper.ItineraryMapper;
//import com.cooksys.mapper.JayMapper;
import com.cooksys.repository.CustomerRepository;
import com.cooksys.repository.CustomerRepository2;
import com.cooksys.repository.FlightRepository;
import com.cooksys.repository.ItineraryRepository;


@Service
public class CustomerService {

//	@Autowired
	private CustomerRepository customerRepository;
//	@Autowired
	private ItineraryMapper itineraryMapper;//JayMapper;
	
	private CustomerRepository2 customerRepository2;
	
	private ItineraryRepository itineraryRepository;
	
	private FlightRepository flightRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ItineraryMapper itinerayMapper, CustomerRepository2 customerRepository2,
			ItineraryRepository itineraryRepository, FlightRepository flightRepository)
	{
		this.customerRepository = customerRepository;
		this.itineraryMapper = itinerayMapper;
		this.customerRepository2 = customerRepository2;
		this.itineraryRepository = itineraryRepository;
		this.flightRepository = flightRepository;
	}
	
	public boolean login(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(credentialsDto.getUsername(), credentialsDto.getPassword());
		if(customer == null)
			return false;
		return true;
	}

	public boolean createAccount(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsername(credentialsDto.getUsername());
		if(customer != null)
			return false;
		Customer newCustomer = new Customer();
		Credentials credentials = new Credentials();
		credentials.setUsername(credentialsDto.getUsername());
		credentials.setPassword(credentialsDto.getPassword());
		newCustomer.setCredentials(credentials);
		customerRepository.save(newCustomer);
		
		return true;
	}

	public List<ItineraryDtoOut> getHistory(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(credentialsDto.getUsername(), credentialsDto.getPassword());
		
		if(customer == null || customer.getHistory() == null)
			return null;
		System.out.println("Customer " + customer.getHistory().size());
		
		List<ItineraryDtoOut> results = itineraryMapper.toDtoOuts(customer.getHistory());
		for(int r = 0; r < results.size(); r++)
		{
			for(int i = 0; i < results.get(r).getFlights().size(); i++)
			{//some mapping problem, I'm too scared of breaking anything to clean install
				results.get(r).getFlights().get(i).setDeparturetime(customer.getHistory().get(r).getFlights().get(i).getDeparturetime());
			}
		}
		
		
		return results;//itineraryMapper.toDtoOuts(customer.getHistory());//Arrays.asList(customer.getHistory()));
	}

	public ItineraryDtoOut postItinerary(ItineraryDtoIn itineraryDtoIn) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(itineraryDtoIn.getCredentials().getUsername(), itineraryDtoIn.getCredentials().getPassword());
		if(customer == null)
			return null;
		System.out.println("Itinerary dto in: " + itineraryDtoIn);
		System.out.println("  A departure time: " + itineraryDtoIn.getFlights().get(0).getDeparturetime());
		Itinerary itinerary = itineraryMapper.toItinerary(itineraryDtoIn);
		System.out.println("Itinerary: " + itineraryDtoIn);
		System.out.println("  B departure time: " + itinerary.getFlights().get(0).getDeparturetime());
		for(int i = 0; i < itinerary.getFlights().size(); i++)
		{//it's not saving properly
			itinerary.getFlights().get(i).setDeparturetime(itineraryDtoIn.getFlights().get(i).getDeparturetime());
		}
		System.out.println("  C departure time: " + itinerary.getFlights().get(0).getDeparturetime());
		itinerary.setCustomer(customer);
		final Itinerary it = itineraryRepository.saveAndFlush(itinerary);
		
		itinerary.getFlights().forEach((flight)->{
			flight.setItinerary(it);
			flightRepository.save(flight);
		});
		
		return itineraryMapper.toDtoOut(itinerary);
	}

}
