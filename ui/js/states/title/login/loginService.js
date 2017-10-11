angular.module('flightApp').service('loginService', ['$http', function ($http) {

    this.login = (submission) => {
        console.dir(submission)
        return $http.post('http://localhost:8000/customers/login/', submission)
    }

}])