angular.module('flightApp').service('viewFlightService', ['userDataService', '$http', function(userDataService, $http){
    this.getFlightList = () => {
        return $http.get('http://localhost:8000/flights')
    }
}])