angular.module('flightApp').service('searchService', ['userDataService', '$http', function (userDataService, $http) {

    // this.createNewTweet = (tweet) => {
    //     return $http.post('http://localhost:8888/api/tweets/', tweet)
    // }
    this.search = (from, to) => {
        alert("not yet implemented " + from + ">" + to)
    }

}])