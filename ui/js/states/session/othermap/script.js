angular.module('appMaps', ['uiGmapgoogle-maps'])
.controller('mainCtrl', function($scope) {
    $scope.map = {center: {latitude: 51.219053, longitude: 4.404418 }, zoom: 14 };
});