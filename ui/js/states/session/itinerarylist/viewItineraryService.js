angular.module('flightApp').service('viewItineraryService', ['userDataService', '$http', function(userDataService, $http){
    
    this.getHistory = () => {
         return $http.post('http://localhost:8000/customers/history/', userDataService.credentials)
    }

    this.getResults = (query) => {
        alert("not yet implemented")
        // return $http.get('http://localhost:8000/flights')
    }

    this.bookIt = (itinerary) => {
        const itineraryDtoIn = 
        {
            flights: itinerary.flights,
            credentials: userDataService.credentials
        }
        return $http.post('http://localhost:8000/customers/createitinerary/', itineraryDtoIn)
    }
}])