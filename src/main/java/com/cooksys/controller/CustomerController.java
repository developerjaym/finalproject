package com.cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.CredentialsDto;
import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Customer;
import com.cooksys.service.CustomerService;

@RestController
@RequestMapping("customers")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value="test")
	public List<Customer> test(HttpServletResponse response)
	{
		return customerService.getAllCredentials();
	}
	
	@RequestMapping(value="testitineraries")
	public String testitineraries(HttpServletResponse response)
	{
		return customerService.getAllItineraries();
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public boolean login(@RequestBody CredentialsDto credentialsDto, HttpServletResponse response)
	{
		boolean successful = customerService.login(credentialsDto);
		if(!successful)
			response.setStatus(404);
		return successful;
	}
	@RequestMapping(value="create", method = RequestMethod.POST)
	public boolean createAccount(@RequestBody CredentialsDto credentialsDto, HttpServletResponse response)
	{
		boolean successful = customerService.createAccount(credentialsDto);
		if(!successful)
			response.setStatus(404);
		return successful;
	}
	@RequestMapping(value="history", method = RequestMethod.POST)
	public List<ItineraryDtoOut> getHistory(@RequestBody CredentialsDto credentialsDto, HttpServletResponse response)
	{
		List<ItineraryDtoOut> results = customerService.getHistory(credentialsDto);
		if(results == null || results.isEmpty())
			response.setStatus(404);
		return results;
	}
	@RequestMapping(value="itinerary", method = RequestMethod.POST)
	public ItineraryDtoOut addItinerary(@RequestBody ItineraryDtoIn itineraryDtoIn, HttpServletResponse response)
	{
		ItineraryDtoOut result = customerService.addItinerary(itineraryDtoIn);
		if(result == null)
			response.setStatus(404);
		return result;
	}
	
	
}
