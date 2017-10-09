angular.module('flightApp').controller('viewItineraryController', ['viewItineraryService', 'userDataService', '$state',
    function (viewItineraryService, userDataService, $state) {

        this.itineraries = [new Itinerary([]), new Itinerary([])]


        //if the user wants the history
        // viewItineraryService.getHistory().then((succeedResponse) => {
        //     console.dir("ITINERARIES: " + succeedResponse.data)
        //     this.itineraries = succeedResponse.data
        // })

        //if the user wants search results
        // viewItineraryService.getResults('some query').then((succeedResponse) => {
        //     console.dir("ITINERARIES: " + succeedResponse.data)
        //     this.itineraries = succeedResponse.data
        // })


        this.getArrivalTime = (flight) => {
            return flight.offset + flight.flightTime
        }

        //I may want other functions to calculate other things

        this.viewMap = (itinerary) => {
            //go to a map state
        }

    }])

    class Itinerary
    {
        constructor(flights)
        {
            this.flights = flights;
            this.flights.push(new Flight("A", "B", 3, 1))
            this.flights.push(new Flight("B", "C", 6, 2))
        }

    }
    class Flight
    {
        constructor(destination, origin, flightTime, offset)
        {
            this.destination = destination;
            this.origin = origin;
            this.flightTime = flightTime;
            this.offset = offset;
        }
    }