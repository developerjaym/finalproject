package com.cooksys.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.pojo.Flight;
import com.cooksys.service.FlightService;
import com.cooksys.service.LocationService;

@RestController
@RequestMapping("flights")
@CrossOrigin
public class FlightsController {
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping
	public ArrayList<FlightDto> getFlightList()
	{
		System.out.println("I'm returning the daily flights! " + flightService.getDailyFlightList() );
		return flightService.getDailyFlightList();
	}
	
	@RequestMapping("/search")
	public ArrayList<ItineraryDtoOut> getSearchResults(@RequestParam("originCityName") String originCityName, @RequestParam("destinationCityName") String destinationCityName, HttpServletResponse response)
	{
		System.out.println("originCityName: " + originCityName);
		System.out.println("destinationCityName: " + destinationCityName);
		ArrayList<ItineraryDtoOut> results = flightService.getSearchResults(originCityName, destinationCityName);
		if(results == null || results.isEmpty())
			response.setStatus(404);
		return results;
	}
}
