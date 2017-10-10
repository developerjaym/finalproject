package com.cooksys.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.CredentialsDto;
import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.Customer;
import com.cooksys.entity.Itinerary;
import com.cooksys.mapper.ItineraryMapper;
//import com.cooksys.mapper.JayMapper;
import com.cooksys.repository.CustomerRepository;
import com.cooksys.repository.CustomerRepository2;
import com.cooksys.repository.ItineraryRepository;


@Service
public class CustomerService {

//	@Autowired
	private CustomerRepository customerRepository;
//	@Autowired
	private ItineraryMapper itineraryMapper;//JayMapper;
	
	private CustomerRepository2 customerRepository2;
	
	private ItineraryRepository itineraryRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository, ItineraryMapper itinerayMapper, CustomerRepository2 customerRepository2,
			ItineraryRepository itineraryRepository)
	{
		this.customerRepository = customerRepository;
		this.itineraryMapper = itinerayMapper;
		this.customerRepository2 = customerRepository2;
		this.itineraryRepository = itineraryRepository;
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
		
		//Customer newCustomer = customerMapper.
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
		System.out.println("Customer " + customer.getId());
		if(customer == null)
			return null;
		System.out.println("History size: " + customer.getHistory().size());
		
		//return JayMapper.toItineraryDtoOuts(customer.getHistory());
		return itineraryMapper.toDtoOuts(customer.getHistory());
	}

	
	public ItineraryDtoOut addItinerary(ItineraryDtoIn itineraryDtoIn) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(itineraryDtoIn.getCredentials().getUsername(), itineraryDtoIn.getCredentials().getPassword());
		if(customer == null)
			return null;
		
		Itinerary itinerary = itineraryRepository.save(itineraryMapper.toItinerary(itineraryDtoIn));
		itinerary.setCustomer(customer);
		customer.getHistory().add(itinerary);
		customer = customerRepository.save(customer);
		
//		I'm stuck here
		
//		customerRepository2.updateHistory(itineraryMapper.toItinerary(itineraryDtoIn), customer);
//		customer.getHistory().add(itineraryMapper.toItinerary(itineraryDtoIn));//JayMapper.toItinerary(itineraryDtoIn, customer));
		System.out.println("some detail " + itineraryMapper.toItinerary(itineraryDtoIn).getFlights().get(0).getFlightTime());
		System.out.println("new history size " + customer.getHistory().size());
//		customerRepository.save(customer);
		//return JayMapper.toItineraryDtoOut(itineraryDtoIn);
		return itineraryMapper.toDtoOut(itineraryDtoIn);
	}

	public List<Customer> getAllCredentials() {
		return customerRepository.findAll();
	}

	public String getAllItineraries() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("List of things:\n");
//		itineraryRepository.findAll().forEach((itinerary)->{
//			sb.append(
//					itinerary.getCustomer()
//					.getCredentials()
//					.getUsername() 
//					+ "\n  from: " + 
//					itinerary
//					.getFlights()
//					.get(0)
//					.getOrigin());
//			});
		
		return sb.toString();
	}
	

//
//	public boolean login(Credentials credentials) {
//		//return true if the username and password match someone
//		return false;
//	}
//
//	public boolean createAccount(Credentials credentials) {
//		//return true if created successfully
//		return false;
//	}

}
