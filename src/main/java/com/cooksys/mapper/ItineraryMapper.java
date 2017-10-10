package com.cooksys.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.dto.ItineraryDtoIn;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Itinerary;

@Mapper(componentModel="spring")
public interface ItineraryMapper {

	List<ItineraryDtoOut> toDtoOuts(List<Itinerary> history);

	Itinerary toItinerary(ItineraryDtoIn itineraryDtoIn);

	ItineraryDtoOut toDtoOut(Itinerary itinerary);

}
