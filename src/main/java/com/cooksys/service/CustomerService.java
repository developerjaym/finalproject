package com.cooksys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooksys.dto.CredentialsDto;
import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Credentials;
import com.cooksys.entity.Customer;
import com.cooksys.mapper.ItineraryMapper;
import com.cooksys.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ItineraryMapper itineraryMapper;
	
	
	public boolean login(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(credentialsDto.getUsername(), credentialsDto.getPassword());
		if(customer == null)
			return false;
		return true;
	}

	public boolean createAccount(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsername(credentialsDto.getUsername());
		if(customer == null)
			return false;
		
		//Customer newCustomer = customerMapper.
		Customer newCustomer = new Customer();
		Credentials credentials = new Credentials();
		credentials.setUsername(credentialsDto.getUsername());
		credentials.setPassword(credentialsDto.getPassword());
		customer.setCredentials(credentials);
		customerRepository.save(newCustomer);
		
		return true;
	}

	public List<ItineraryDtoOut> getHistory(CredentialsDto credentialsDto) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(credentialsDto.getUsername(), credentialsDto.getPassword());
		if(customer == null)
			return null;
		return itineraryMapper.toDtoOuts(customer.getHistory());
	}

	public ItineraryDtoOut addItinerary(ItineraryDtoIn itineraryDtoIn) {
		Customer customer = customerRepository.findByCredentialsUsernameAndCredentialsPassword(itineraryDtoIn.getCredentials().getUsername(), itineraryDtoIn.getCredentials().getPassword());
		if(customer == null)
			return null;
		customer.getHistory().add(itineraryMapper.toItinerary(itineraryDtoIn));
		return itineraryMapper.toDtoOut(itineraryMapper.toItinerary(itineraryDtoIn));
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
