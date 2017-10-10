angular.module('flightApp').service('userDataService', ['$state', function ($state) {

    this.credentials = new Credentials(undefined, undefined)

    this.setCustomerCredentials = (username, password) => {
        this.credentials.username = username
        this.credentials.password = password
    }

    this.loggedIn = () => (this.credentials.username !== undefined && this.credentials.password !== undefined)

    // this.followersNum = 0
    // this.followingNum = 0

    this.sessionTitleEnum = {
        DOCUMENT: 'Document',
        FLIGHT: 'Flights',
        ISSUER: 'Issuer',
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
    }

    this.buildCustomer = (username, password) => {
        const cred = new Credentials(username, password)

        return cred
    }


    // this.sessionTypeEnum = {
    //     DOCUMENT: 'DOCUMENT'
    //     MAIN: 'MAIN',
    //     CUSTOM: 'CUSTOM',
    //     SINGLE: 'SINGLE',
    //     CONTEXT: 'CONTEXT',
    //     USER: 'USER',
    //     HASHTAG: 'HASHTAG'
    // }

    // this.activeFeed = this.feedTypeEnum.MAIN
    // this.feedDependency = undefined


    // this.activeUserList = this.userListTypeEnum.ALL
    // this.userListDependency = undefined

    this.reloadIfNecessary = (stateName, optionalPrefix) => {
        console.dir("Reloading")
        switch (stateName) {
            case 'session.flight':
                this.setSessionTitle('View All Flights')
                break;
            case 'session.search':
                this.setSessionTitle('Search For Flights')
                break;    
            case 'session.history':
                this.setSessionTitle('Your Past Itineraries')
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


