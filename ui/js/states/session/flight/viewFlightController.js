angular.module('flightApp').controller('viewFlightController', ['viewFlightService', 'userDataService', '$state', '$interval',
    function(viewFlightService, userDataService, $state, $interval){

        this.flights = []

        viewFlightService.getFlightList().then((succeedResponse)=>{
            this.flights = succeedResponse.data
        })

        this.hidePlural = (number) => {
            if(number != 1)
                return false;
            else
                return true
        }

        this.getArrivalTime = (flight)=>{
            return flight.departuretime+flight.flightTime
        }

        $interval(()=>{this.reload()}, 5000);
        

        this.reload = () =>{
            viewFlightService.getFlightList().then((succeedResponse)=>{
                this.flights = succeedResponse.data
            })
        }
        if (!userDataService.loggedIn()) {
            $state.go('title.login')
        }

        
    }])

    