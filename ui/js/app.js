angular.module('flightApp', ['ui.router', 'xeditable', 'ngMap']).run(function (editableOptions) {
    editableOptions.theme = 'bs3';
}).config(['$stateProvider', '$urlRouterProvider',
    function ($stateProvider, $urlRouterProvider) {

        const titleState = {
            abstract: true,
            name: 'title',
            url: '/title',
            component: 'titleComponent'
        }

        const loginState = {
            name: 'title.login',
            url: '/login',
            component: 'loginComponent'
        }

        const newUserState = {
            name: 'title.newuser',
            url: '/newuser',
            component: 'newUserComponent'
        }

        const sessionState = {
            abstract: true,
            name: 'session',
            url: '/session',
            component: 'sessionComponent'
        }

        const flightState = {
            name: 'session.flight',
            url: '/flight',
            component: 'viewFlightComponent'
        }

        const searchState = {
            name: 'session.search',
            url: '/search',
            component: 'searchComponent'
        }

        const historyState = {
            name: 'session.history',
            url: '/history',
            component: 'viewItineraryComponent'
        }

        // const resultsState = {
        //     name: 'session.results',
        //     url: '/results',
        //     component: 'resultsComponent' or maybe 'viewItineraryComponent'
        // }

        const mapState = {
            name: 'session.map',
            url: '/map',
            component: 'mapComponent'
        }

        $stateProvider.state(titleState)
            .state(loginState)
            .state(newUserState)
            .state(sessionState)
            .state(flightState)
            .state(searchState)
            .state(historyState)
            // .state(resultsState)
            .state(mapState)

        $urlRouterProvider.otherwise('/title/login')
        // $urlRouterProvider.otherwise('/session/map')
    }
])