angular.module('flightApp').service('userDataService', ['$state', '$window', function ($state, $window) {

    this.credentials = new Credentials(undefined, undefined)

    this.setCustomerCredentials = (username, password) => {
        this.credentials.username = username
        this.credentials.password = password
    }

    this.loggedIn = () => (this.credentials.username !== undefined && this.credentials.password !== undefined)

    this.currentItinerary = undefined;

    //this.locations = ["MEMPHIS", "NASHVILLE"]
    //console.dir(this.locations)

    this.sessionTitleEnum = {
        FLIGHT: 'Flights',
        SEARCH: 'Search Results'
    }

    this.sessionTitle = this.sessionTitleEnum.FLIGHT;

    this.setSessionTitle = (sessionType, optionalPrefix) => {
        if (optionalPrefix === undefined) {
            optionalPrefix = ''
        }

        this.sessionTitle = optionalPrefix + sessionType;
    }

    this.logout = () => {
        this.credentials.username = undefined
        this.credentials.password = undefined
        $window.location.reload()
    }

    this.buildCustomer = (username, password) => {
        const cred = new Credentials(username, password)

        return cred
    }

    this.searchResults = undefined;

    this.reloadIfNecessary = (stateName, optionalPrefix) => {
        switch (stateName) {
            case 'session.flight':
                this.setSessionTitle('View All Flights')
                break;
            case 'session.search':
                this.setSessionTitle('Search For Flights')
                break;    
            case 'session.history':
                this.setSessionTitle('Itineraries')
                break;     
            case 'session.results':
                this.setSessionTitle('Search Results')
                break;
            case 'session.map':
                this.setSessionTitle('Map')
                break;           
            default:
                break;
        }

        if ($state.is(stateName)) {
            $state.reload()
        } else {
            $state.go(stateName)
        }
    }

}])




class Credentials {
    constructor(username, password) {
        this.username = username
        this.password = password
    }

    getUsername() {
        this.username
    }
    getPassword() {
        this.password
    }
}


