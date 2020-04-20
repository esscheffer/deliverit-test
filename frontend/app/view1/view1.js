'use strict';

let view1 = angular.module('myApp.view1', ['ngRoute'])

view1.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
        templateUrl: 'view1/view1.html',
        controller: 'View1Ctrl'
    });
}]);

view1.controller('View1Ctrl', ['$scope', '$http', function (scp, http) {
    scp.bills = [];
    scp.init = function () {
        http.get("http://localhost:8081/bill")
            .then(function (response) {
                scp.bills = response.data;
            })
            .catch(function (exception) {
                console.log("exception:", exception);
            });
    }
}]);
