
angular.module('flightApp').controller('mapController', ['mapService', 'userDataService', '$state',
function (mapService, userDataService, $state) {
    // this.locations = {
    //     // chattanooga: {
    //     //   latitude: 35.0457, 
    //     //   longitude: -85.3096
    //     // },
      
    //     memphis: {
    //       latitude: 35.1495,
    //       longitude: -90.0490
    //     },
      
    //     nashville: {
    //       latitude: 36.1627,
    //       longitude: -86.7816
    //     },
      
    //     knoxville: {
    //       latitude: 35.9606,
    //       longitude: -83.9207
    //     }
    //   }

        
                this.zoom = 7
                this.center = [35.5175, -86.5804]
        
            
            //   // add markers from an angular constant
            //   const { memphis, nashville, knoxville } = this.locations
            //   this.markers = [memphis, nashville, knoxville]
          
            //   this.markers.forEach(marker => this.addMarker(marker))
          
            //   // add paths manually
            //   this.paths = [
            //     [memphis, nashville, '#CC0099'],
            //     [nashville, knoxville, '#AA1100']
            //   ]
          
            //   this.paths.forEach(args => this.addPath(...args))
          
            //   // add path from webservice
            //   mapService.getMarkerByCityName('Chattanooga')
            //     .then(chattanooga => {
            //       this.addPath(knoxville, chattanooga, '#FF3388')
            //     })
            
            // this.addMarker = ({ latitude, longitude }) =>{
            //   this.markers.push({
            //     position: `[${latitude}, ${longitude}]`
            //   })
            // }
          
            // this.addPath = (a, b, color) => {
            //   this.paths.push({
            //     path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
            //     strokeColor: color,
            //     strokeOpacity: 1.0,
            //     strokeWeight: 3,
            //     geodesic: true
            //   })
            // }



    // this.controller = new Mapper(mapService, this.locations)

}])

class Mapper {
    // zoom = 7
    // center = [35.5175, -86.5804]
    // markers = []
    // paths = []
  
    constructor ($map, locations) {

        this.zoom = 7
        this.center = [35.5175, -86.5804]
        this.markers = []
        this.paths = []

      this.$map = $map
    
      // add markers from an angular constant
      const { memphis, nashville, knoxville } = locations
      const markers = [memphis, nashville, knoxville]
  
      this.markers.forEach(marker => this.addMarker(marker))
  
      // add paths manually
      const paths = [
        [memphis, nashville, '#CC0099'],
        [nashville, knoxville, '#AA1100']
      ]
  
      this.paths.forEach(args => this.addPath(...args))
  
      // add path from webservice
      $map.getMarkerByCityName('Chattanooga')
        .then(chattanooga => {
          this.addPath(knoxville, chattanooga, '#FF3388')
        })
    }
  
    addMarker ({ latitude, longitude }) {
      this.markers.push({
        position: `[${latitude}, ${longitude}]`
      })
    }
  
    addPath (a, b, color) {
      this.paths.push({
        path: `[[${a.latitude}, ${a.longitude}], [${b.latitude}, ${b.longitude}]]`,
        strokeColor: color,
        strokeOpacity: 1.0,
        strokeWeight: 3,
        geodesic: true
      })
    }
  
  }