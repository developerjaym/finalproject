package com.cooksys.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.entity.Customer;
import com.cooksys.entity.Itinerary;
import com.cooksys.pojo.Flight;
import com.cooksys.service.CustomerService;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
//	@RequestMapping(method = RequestMethod.POST)
//	public boolean login(@RequestBody Credentials credentials, HttpServletResponse response)
//	{
//		boolean successful = customerService.login(customer);
//		if(!successful)
//			response.setStatus(404);
//		return false;
//	}
//	@RequestMapping(value="create", method = RequestMethod.POST)
//	public boolean createAccount(@RequestBody Credentials credentials, HttpServletResponse response)
//	{
//		boolean successful = customerService.createAccount(credentials);
//		if(!successful)
//			response.setStatus(404);
//		return false;
//	}
//	@RequestMapping(value="itinerary", method = RequestMethod.POST)
//	public Itinerary addItinerary(@RequestBody Itinerary itinerary, HttpServletResponse response)
//	{
//		
//		return null;
//	}
	
}
