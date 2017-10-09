angular.module('flightApp').component('sessionComponent', {

    templateUrl: './js/states/session/sessionTemplate.html',
    controller: 'sessionController',
    bindings: {
        search: '='
    }

})