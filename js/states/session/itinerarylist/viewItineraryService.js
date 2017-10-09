angular.module('flightApp').service('viewItineraryService', ['userDataService', '$http', function(userDataService, $http){
    
    this.getHistory = (user) => {
        alert("not yet implemented")
        // return $http.get('http://localhost:8000/flights')
    }

    this.getResults = (query) => {
        alert("not yet implemented")
        // return $http.get('http://localhost:8000/flights')
    }
}])