package com.cooksys.component;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cooksys.dto.FlightDto;
import com.cooksys.dto.ItineraryDtoOut;
import com.cooksys.entity.Location;

@Component
public class Searcher {
	
	public static final HashMap<String, Long> NAMEMAP = new HashMap<>();
	
	public ArrayList<ItineraryDtoOut> searchFlights(String originCityName, String destinationCityName, List<FlightDto> flightList, List<Location> locationList)
	{
		NAMEMAP.put("memphis", 4L);
		NAMEMAP.put("nashville", 3L);
		NAMEMAP.put("knoxville", 2L);
		NAMEMAP.put("chattanooga", 1L);
		
		Graph graph = new Graph(originCityName, destinationCityName, flightList, locationList);
		//find paths
		//then rule out paths based on departure times and layovers
	 	//then sort
		return graph.getPaths();
	}
	/*
	public static void main(String[] args)
	{
		System.out.println("Test");
		FlightDto[] flightArr = { 
				new FlightDto("nashville", "chattanooga", 3, 1),
				new FlightDto("nashville", "memphis", 1, 2),	
				new FlightDto("knoxville", "nashville", 2, 2),
				new FlightDto("memphis", "nashville", 1, 4),
				new FlightDto("chattanooga", "memphis", 2, 8),
		};
//		
//		FlightDto[] flightArr = { 
//				new FlightDto("nashville", "chattanooga", 3, 1),
//				new FlightDto("nashville", "memphis", 1, 2),	
//				new FlightDto("knoxville", "nashville", 2, 2),
//				new FlightDto("memphis", "nashville", 1, 4),
//				new FlightDto("chattanooga", "memphis", 2, 8),
//		};
//		
		Location[] locationArr = {
				new Location(1L, "long", "lat", "chattanooga"),
				new Location(2L, "long", "lat", "knoxville"),
				new Location(3L, "long", "lat", "nashville"),
				new Location(4L, "long", "lat", "memphis"),
		};
	
		ArrayList<ItineraryDtoOut> searchResults = searchFlights("nashville", "memphis", Arrays.asList(flightArr), Arrays.asList(locationArr));
		//returned null, which is correct
		System.out.println("Results are : " + searchResults);
		
//		ArrayList<ItineraryDtoOut> searchResults = searchFlights("memphis", "knoxville", Arrays.asList(flightArr), Arrays.asList(locationArr));
//		//returned null, which is correct
//		System.out.println("Results are : " + searchResults);
		
//		ArrayList<ItineraryDtoOut> searchResults = searchFlights("memphis", "nashville", Arrays.asList(flightArr), Arrays.asList(locationArr));
//		//returned a direct flight which is correct
//		System.out.println("Results are : " + searchResults);
		
//		ArrayList<ItineraryDtoOut> searchResults = searchFlights("memphis", "chattanooga", Arrays.asList(flightArr), Arrays.asList(locationArr));
//		//returned two flights!!!!!!
//		System.out.println("Results are : " + searchResults);
		
	}
	*/
}

class Graph//Tennessee
{
	List<Edge> edges;
	List<Vertex> vertices;
	Set<Path> paths;
	long startingPoint;
	long endingPoint;
	Graph(String originCityName, String destinationCityName, List<FlightDto> flightList, List<Location> locationList)
	{
		vertices = new ArrayList<>();
			locationList.forEach((location)->{
				vertices.add(new Vertex(location.getId(), location));
			});
		edges = new ArrayList<>();
			flightList.forEach((flight)->{
				edges.add(new Edge(Searcher.NAMEMAP.get(flight.getOrigin().toLowerCase()), Searcher.NAMEMAP.get(flight.getDestination().toLowerCase()), flight));
			});
			System.out.println("****STARTING AT ****: " + originCityName.toLowerCase());
			System.out.println("****ENDING AT ****: " + destinationCityName.toLowerCase());
		startingPoint = Searcher.NAMEMAP.get(originCityName.toLowerCase());
		endingPoint = Searcher.NAMEMAP.get(destinationCityName.toLowerCase());
		
		paths = new HashSet<>();
		
		System.out.println("START SEARCH");
		System.out.println("**********************************************");
		List<Long> visited = new ArrayList<>();
		long mustBeOneHourAfter = -1l;
		searchEdges(mustBeOneHourAfter, visited, edges, startingPoint, endingPoint, new Path());
	}
	
	
	
	public ArrayList<ItineraryDtoOut> getPaths() {
		ArrayList<ItineraryDtoOut> results = new ArrayList<>();
		System.out.println("*ALL PATHS*");
		paths.forEach((path)->{
			System.out.println("***PATH***");
			path.getEdges().forEach((edge)->{
				System.out.println("*****" + edge.getStart() + " > " + edge.getEnd() + "*****");
			});
			results.add(path.getItinerary());
		});
		
		return results;
	}



	void searchEdges(long mustBeOneHourAfter, List<Long> visited, List<Edge> allEdges, long start, long end, Path currentPath)
	{
		ArrayList<Edge> goodStarts = new ArrayList<>();
		ArrayList<Edge> none = new ArrayList<>();
		ArrayList<Edge> goodEnds = new ArrayList<>();
		//divide the list of edges
		allEdges.forEach((edge)->{
			if(edge.getEnd() == end)
			{
				goodEnds.add(edge);
			}
			if(edge.getStart() == start)
			{
				goodStarts.add(edge);
			}
			if(edge.getStart() != start && edge.getEnd() != end)
			{
				none.add(edge);
			}			
		});
		
		//check good starts for members of good ends
			//if there is overlap, take my current path and make a path for each edge that is in both categories
		ArrayList<Edge> overlap = new ArrayList<>();
		goodStarts.forEach((edge)->{
			if(goodEnds.contains(edge))
			{
				overlap.add(edge);
				Path p = new Path();//make a new path for each 'branch'
				currentPath.getEdges().forEach((ed)->{
					p.addEdge(ed);//fill this new path with the beginnings of the previous branch
				});
				p.addEdge(edge);//put the most recent edge into the 'branch'
				if(currentPath.getEdges().isEmpty() 
						|| 
					(edge.getCorrespondingObject().getDeparturetime()/*+edge.getCorrespondingObject().getFlightTime()*/) > (currentPath.getEdges().get(currentPath.getEdges().size()-1).getCorrespondingObject().getDeparturetime()+ currentPath.getEdges().get(currentPath.getEdges().size()-1).getCorrespondingObject().getFlightTime()))
				{//knock out flights that aren't at the right time (edge (the new flight being added) must take off AFTER the previously-added flight has landed
					paths.add(p);//add this path to our list of acceptable paths
					visited.add(edge.getStart());//to prevent it from trying to fly me around the state a bunch for no reason
					return;
				}
				
			}
		});
		
		//now let's focus on 'none' and 'goodends'
			//we need to somehow iterate and find 'connections'
				//we need to somehow stop the iteration if no possible path exists
					//(We have no paths with acceptable ends or beginnings?)
		if(goodStarts.size() == 0 || goodEnds.size() == 0)
		{
			System.out.println("  NO ACCEPTABLE PATH - returning");
			return;
		}
		
		//for each 'goodStart', call searchEdges() with that that edge's endPoint as the new start
		goodStarts.forEach((edge)->{
		//newGoodStarts.forEach((edge)->{
			if(visited.contains(edge.getStart()))
			{
				System.out.println("  Already visited " + edge.getStart());
				return;
			}
			ArrayList<Edge> newAll = new ArrayList<>();
			newAll.addAll(none);
			newAll.addAll(goodEnds);
			visited.add(edge.getStart());
			currentPath.addEdge(edge);
			searchEdges(mustBeOneHourAfter, visited, newAll, edge.getEnd(), end, currentPath);
			
		});
		//System.out.println("Just curious how often I get here");	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (end ^ (end >>> 32));
		result = prime * result + (int) (start ^ (start >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
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
	ItineraryDtoOut getItinerary()
	{
		ItineraryDtoOut itineraryDtoOut = new ItineraryDtoOut();
		final List<FlightDto> flights = new ArrayList<>();
		path.forEach((edge)->{
			flights.add(edge.getCorrespondingObject());
		});
		itineraryDtoOut.setFlights(flights);
		return itineraryDtoOut;
	}
	List<Edge> getEdges()
	{
		return path;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
}
