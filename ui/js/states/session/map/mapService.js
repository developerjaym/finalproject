angular.module('flightApp').service('mapService', ['userDataService', '$http', function(userDataService, $http){
    
    this.getMarkerByCityName = (name)=> {
        return $http
          .get(`http://localhost:8000/location/name`, { params: { name } })
          //.then(result => result.data)
          //longitude, latitude, city
      }

      
}])