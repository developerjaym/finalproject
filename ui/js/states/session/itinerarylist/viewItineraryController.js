angular.module('flightApp').controller('viewItineraryController', ['mapService', 'viewItineraryService', 'userDataService', '$state',
    function (mapService, viewItineraryService, userDataService, $state) {

        //this.itineraries = [new Itinerary([]), new Itinerary([])]
        this.itineraries = []

        
        this.isBookItHidden = false;


       
        if(userDataService.searchResults) //if the user wants search results
        {
            this.isBookItHidden = false;
            this.itineraries = userDataService.searchResults
        }
        else// if the user wants the history
        {
            this.isBookItHidden = true;
            viewItineraryService.getHistory().then((succeedResponse) => {
                console.dir("ITINERARIES: " + succeedResponse.data)
                this.itineraries = succeedResponse.data
            })
        }
        

        this.hidePlural = (number) => {
            if(number != 1)
                return false;
            else
                return true
        }

        this.bookIt = (itinerary) => {
            //alert("booking requested for " + itinerary)
            viewItineraryService.bookIt(itinerary).then((succeedResponse) => {
                userDataService.searchResults = undefined;
                userDataService.reloadIfNecessary('session.history')
                //this.itineraries.push(succeedResponse.data)
            })
        }

        this.getArrivalTime = (flight) => {
            return flight.departuretime + flight.flightTime
        }

        this.getTotalFlightTime = (itinerary) => {
            return itinerary.flights.reduce((previouslyReturned, currentFlight)=>{
                return (currentFlight.flightTime + previouslyReturned)
            }, 0)
        }

        this.getTotalLayoverTime = (itinerary) => {
            const start = itinerary.flights[0].departuretime
            const end = this.getArrivalTime(itinerary.flights[itinerary.flights.length-1])
            const total = end-start;
            return (total-this.getTotalFlightTime(itinerary))
        }

        this.findOrigin = (itinerary) =>{
            return itinerary.flights[0].origin
        }

        this.findDestination = (itinerary) =>{
            return itinerary.flights[itinerary.flights.length-1].destination
        }

        this.shouldHide = () =>{
            //alert(userDataService.searchResults);
            if(userDataService.searchResults)
                return false;
        }

        //I may want other functions to calculate other things

        this.viewMap = (itinerary) => {
            //go to a map state
            userDataService.reloadIfNecessary('session.map')
        }

    }])

    class Itinerary
    {
        constructor(flights)
        {
            this.flights = flights;
            this.flights.push(new Flight("MEMPHIS", "NASHVILLE", 3, 1))
            this.flights.push(new Flight("NASHVILLE", "KNOXVILLE", 6, 2))
        }

    }
    class Flight
    {
        constructor(destination, origin, flightTime, departuretime)
        {
            this.destination = destination;
            this.origin = origin;
            this.flightTime = flightTime;
            this.departuretime = departuretime;
        }
    }