angular.module('flightApp').service('newUserService', ['$http', function ($http) {

    this.createNewUser = (user) => {
        return $http.post('http://localhost:8888/api/customer/', user)
    }

}])