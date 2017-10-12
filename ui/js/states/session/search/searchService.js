angular.module('flightApp').service('searchService', ['userDataService', '$http', function (userDataService, $http) {

    this.lastFrom = "MEMPHIS";
    this.lastTo = "NASHVILLE";

    this.search = (from, to) => {

        if (!from)
            from = this.lastFrom
        else
            this.lastFrom = from

        if (!to)
            to = this.lastTo
        else
            this.lastTo = to
        return $http.get('http://localhost:8000/flights/search?originCityName=' + from + "&destinationCityName=" + to)
    }

}])