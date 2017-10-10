angular.module('flightApp').controller('viewFlightController', ['viewFlightService', 'userDataService', '$state', '$interval',
    function(viewFlightService, userDataService, $state, $interval){

        // this.submission = {}//for posting something
        //do submission stuff later

        
        // this.username = userDataService.credentials.getUsername()
        this.flights = []

        viewFlightService.getFlightList().then((succeedResponse)=>{
            console.dir("FLIGHTS: " + succeedResponse.data)
            this.flights = succeedResponse.data
        })

        this.getArrivalTime = (flight)=>{
            return flight.offset+flight.flightTime
        }

        $interval(()=>{this.reload()}, 500000);
        

        this.reload = () =>{
            viewFlightService.getFlightList().then((succeedResponse)=>{
                console.dir("reloading")
                this.flights = succeedResponse.data
            })
        }

    }])

    