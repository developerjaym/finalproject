package com.cooksys.service;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cooksys.component.FlightGenerator;
import com.cooksys.component.Searcher;
import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.pojo.Flight;
import com.cooksys.repository.LocationRepository;

@Service
public class FlightService {

	@Autowired
	FlightGenerator generator;

	@Autowired
	Searcher searcher;

	@Autowired
	LocationRepository locationRepository;
	
	private ArrayList<FlightDto> flightList = new ArrayList<>();

	public ArrayList<FlightDto> getDailyFlightList() {
		flightList.sort(new Comparator<FlightDto>() {

			@Override
			public int compare(FlightDto o1, FlightDto o2) {
				// Returns
				// a negative integer: the first argument is less than
				// zero: the first argument is equal to
				// a positive integer: athe first argument is greater than the
				// second.
				if (o1.getDeparturetime() > o2.getDeparturetime())
					return 1;
				else if (o1.getDeparturetime() == o2.getDeparturetime())
					return 0;
				else
					return -1;
			}

		});
		return flightList;
	}

	// The fixedDelay parameter determines how often a new day is generated as
	// expressed in milliseconds
	@Scheduled(fixedDelay = 500000) // (fixedDelay=5000)
	private void refreshFlights() {
		flightList = generator.generateNewFlightList();
	}

	public ArrayList<ItineraryDtoOut> getSearchResults(String originCityName, String destinationCityName) {
		// TODO Auto-generated method stub
		ArrayList<ItineraryDtoOut> results = searcher.searchFlights(originCityName, destinationCityName, flightList, locationRepository.findAll());
		results.sort(new Comparator<ItineraryDtoOut>() {

			@Override
			public int compare(ItineraryDtoOut o1, ItineraryDtoOut o2) {
				// Returns
				// a negative integer: the first argument is less than
				// zero: the first argument is equal to
				// a positive integer: athe first argument is greater than the
				// second.
				if (getTotalTime(o1) > getTotalTime(o2))
					return 1;
				else if (getTotalTime(o1) == getTotalTime(o2))
					return 0;
				else
					return -1;
			}

		});

		return results;
	}

	protected long getTotalTime(ItineraryDtoOut o1) {
		long totalTime = 0L;
		for(FlightDto f : o1.getFlights())
		{
			totalTime = totalTime + f.getFlightTime();
		}
		return totalTime;
	}

}
