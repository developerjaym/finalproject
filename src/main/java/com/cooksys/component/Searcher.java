package com.cooksys.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Location;

@Component
public class Searcher {
	
	public static final HashMap<String, Long> NAMEMAP = new HashMap<>();
	
	public ArrayList<ItineraryDtoOut> searchFlights(String originCityName, String destinationCityName, ArrayList<FlightDto> flightList, List<Location> locationList)
	{
		NAMEMAP.put("memphis", 4L);
		NAMEMAP.put("nashville", 3L);
		NAMEMAP.put("knoxville", 2L);
		NAMEMAP.put("chattanooga", 1L);
		
		Graph graph = new Graph(originCityName, destinationCityName, flightList, locationList);
		//find paths
		//then rule out paths based on departure times and layovers
		//then sort
		return null;
	}
	
}

class Graph//Tennessee
{
	List<Edge> edges;
	List<Vertex> vertices;
	List<Path> paths;
	long startingPoint;
	long endingPoint;
	Graph(String originCityName, String destinationCityName, ArrayList<FlightDto> flightList, List<Location> locationList)
	{
		vertices = new ArrayList<>();
			locationList.forEach((location)->{
				vertices.add(new Vertex(location.getId(), location));
			});
		edges = new ArrayList<>();
			flightList.forEach((flight)->{
				edges.add(new Edge(Searcher.NAMEMAP.get(flight.getOrigin().toLowerCase()), Searcher.NAMEMAP.get(flight.getDestination().toLowerCase()), flight));
			});
		startingPoint = Searcher.NAMEMAP.get(originCityName.toLowerCase());
		endingPoint = Searcher.NAMEMAP.get(destinationCityName);
		
		paths = new ArrayList<>();
	}
	void search(Long origin)
	{
		Path newPath = new Path();
		
	}
	Path traverse(Path p, ArrayList<Vertex> visited, Vertex checkFrom, Vertex destination)
	{
		List<Vertex> neighbors = getNeighbors(checkFrom);
		if(neighbors.contains(destination))
		{
			//add the connecting edge to p
			return p;
		}
		if(neighbors.isEmpty())
		{//we can't fly out of here
			return null;
		}
		if(visited.containsAll(neighbors))
		{//nothing new to check
			
		}
//		if(neighbors.size() == 1 && visited.contains(neighbors.get(0)))
//		{//we are back where we started
//			return null;
//		}
		
		
		//add the edge to p
		//add checkFrom to visited
		//set checkFrom to something new
		return traverse(p, visited, checkFrom, destination);//we aren't stuck, we aren't back, and we aren't at the destination
	}
	List<Vertex> getNeighbors(Vertex x)
	{
		List<Vertex> neighbors = new ArrayList<>();
		vertices.forEach((y)->{
			edges.forEach((edge)->{
				//if edge connects x to y, put it in the list
				if(edge.getStart() == x.getId() && edge.getEnd() == y.getId())
					neighbors.add(y);
			});
		});
		return neighbors;
	}
}

class Edge//flights
{
	long start;
	long end;
	FlightDto correspondingObject;
	Edge(long start, long end, FlightDto correspondingObject)
	{
		this.start = start;
		this.end = end;
		this.correspondingObject = correspondingObject;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public FlightDto getCorrespondingObject() {
		return correspondingObject;
	}
	public void setCorrespondingObject(FlightDto correspondingObject) {
		this.correspondingObject = correspondingObject;
	}
	
}

class Vertex//locations
{
	long id;
	Location correspondingObject;
	Vertex(long id, Location correspondingObject)
	{
		this.id = id;
		this.correspondingObject = correspondingObject;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Location getCorrespondingObject() {
		return correspondingObject;
	}
	public void setCorrespondingObject(Location correspondingObject) {
		this.correspondingObject = correspondingObject;
	}
	
}

class Path//itineraries
{
	List<Edge> path;
	Path(){path = new ArrayList<Edge>();}
	void addEdge(Edge edge)
	{
		path.add(edge);
	}
	ItineraryDtoOut getPath()
	{
		ItineraryDtoOut itineraryDtoOut = new ItineraryDtoOut();
		final List<FlightDto> flights = new ArrayList<>();
		path.forEach((edge)->{
			flights.add(edge.getCorrespondingObject());
		});
		itineraryDtoOut.setFlights(flights);
		return itineraryDtoOut;
	}
}
