
angular.module('flightApp')
  .controller('mapController', ['mapService', 'userDataService', '$state',
    function (mapService, userDataService, $state) {
      
      if (!userDataService.loggedIn()) {
        $state.go('title.login')
    }
      
      //35.5175, -86.5804
      this.map = { center: { latitude: 35.5175, longitude: -86.5804 }, zoom: 7 };


      this.getColor = (path) =>
      {
        const r = path[0][0];
        const g = path[1][0];
        const b = path[1][1];
        return "rgb(" + Math.abs(r) +", " + Math.abs(g) + ", " + Math.abs(b) + ")"
      }

      this.getRandomColor = () =>
      {
        const r = Math.floor((Math.random() * 255) + 1);
        const g = Math.floor((Math.random() * 255) + 1);
        const b = Math.floor((Math.random() * 255) + 1);
        console.dir("Color " + "rgb(" + r +", " + g + ", " + b + ")")
        return "rgb(" + r +", " + g + ", " + b + ")"
      }

      this.flights = userDataService.currentItinerary.flights
      this.cities = this.flights.reduce((previouslyReturned, currentElement) => {
        previouslyReturned.push(currentElement.origin)
        previouslyReturned.push(currentElement.destination)
        return previouslyReturned
      }, [])

      this.othermarkers = this.flights.reduce((previouslyReturned, currentElement) => {

        previouslyReturned.push(
          {
            position: currentElement.origin
          }
        )
        previouslyReturned.push(
          {
            position: currentElement.destination
          }
        )
        return previouslyReturned
      }, [])
      this.paths = []

      this.citymap =
        {
          'CHATTANOOGA':
          {
            lat: 35.0457,
            lng: -85.3096
          },

          'KNOXVILLE':
          {
            lat: 35.9606,
            lng: -83.9207
          },

          'NASHVILLE':
          {
            lat: 36.1627,
            lng: -86.7816
          },

          'Memphis':
          {
            lat: 35.1495,
            lng: -90.0490
          },
        }
        // let x;
        // for(x in this.citymap)
        // {
        //   let firstChar = x.charAt(0);
        //   let name = x.toLowerCase();
        //   name = firstChar + name.substr(1)
        //   console.dir("adjust name: " + name)
        //   mapService.getMarkerByCityName(name).then((succeedResponse)=>{
        //     console.dir("RESPONSE " + JSON.stringify(succeedResponse.data))
        //     this.citymap[x].lat = succeedResponse.data.latitude
        //     this.citymap[x].lng = succeedResponse.data.longitude
        //   })
        // }
        
        


      this.markers = this.cities.reduce((previouslyReturned, currentCity) => {
        let newMarker = this.citymap[currentCity]
        //use service to get 
        previouslyReturned.push(newMarker)
        return previouslyReturned
      }
        , [])

      this.path = this.markers.map( (marker)=> {
        return [marker.lat, marker.lng, this.getRandomColor()];
      });

      this.paths.push(this.path);

      
      this.paths2 = []
      //for each flight, make a path with two cities, push it to paths2
      this.flights.forEach((flight)=>{
        this.paths2.push([[this.citymap[flight.origin].lat , this.citymap[flight.origin].lng], [this.citymap[flight.destination].lat , this.citymap[flight.destination].lng]])//origin then destination
        
      })
      

    }])

