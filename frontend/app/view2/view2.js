'use strict';

let view2 = angular.module('myApp.view2', ['ngRoute'])

view2.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view2', {
        templateUrl: 'view2/view2.html',
        controller: 'View2Ctrl'
    });
}]);

view2.controller('View2Ctrl', ['$scope', '$http', function (scp, http) {
    scp.bill = {};

    scp.update = function (bill) {
        scp.bill = angular.copy(bill);

        let billToSend = angular.copy(bill);

        billToSend.dueDate = convertDate(scp.bill.dueDate);
        billToSend.paymentDate = convertDate(scp.bill.paymentDate);

        http.post("http://localhost:8081/bill", billToSend)
            .then(function (response) {
                scp.bill = {};
                alert("Bill added successfully!");
                scp.reset();
            })
            .catch(function (exception) {
                console.log("exception:", exception);
            });
    };

    scp.reset = function (form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
        scp.bill = angular.copy(scp.bill);
    };

    scp.reset();
}]);

function convertDate(date) {
    return moment(date).format('YYYY-MM-DD');
}
