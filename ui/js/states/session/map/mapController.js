
angular.module('flightApp', ['uiGmapgoogle-maps'])
.controller('mapController', ['mapService', 'userDataService', '$state',
function (mapService, userDataService, $state) {
  //35.5175, -86.5804
   this.map = { center: { latitude: 35.5175, longitude: -86.5804 }, zoom: 7 };

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