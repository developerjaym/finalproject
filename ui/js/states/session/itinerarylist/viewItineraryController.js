angular.module('flightApp').controller('viewItineraryController', ['mapService', 'viewItineraryService', 'userDataService', '$state',
    function (mapService, viewItineraryService, userDataService, $state) {

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
        constructor(destination, origin, flightTime, offset)
        {
            this.destination = destination;
            this.origin = origin;
            this.flightTime = flightTime;
            this.offset = offset;
        }
    }