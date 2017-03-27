var app = angular.module('navbarApp', ['ngMaterial', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);

app.controller('NavCtrl', function($scope, $mdDialog) {
    $scope.customFullscreen = false;

    $scope.showLoginDialog = function(ev) {
        $mdDialog.show( {
            controller: DialogController,
            contentElement: '#dialog-login',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true
    });
    };

    $scope.showRegisterDialog = function(ev) {
        $mdDialog.show( {
            controller: DialogController,
            contentElement: '#dialog-register',
            parent: angular.element(document.body),
            targetEvent: ev,
            clickOutsideToClose: true
        });
    };

    function DialogController($scope, $mdDialog) {

        $scope.close = function() {
            $mdDialog.cancel();
        };

    }


});

app.controller('LoginFormSubmitCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.list = [];
    $scope.loginError = false;

    //$scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
    $scope.submit = function() {
        var loginData = {
            "email" : $scope.email,
            "password" : $scope.password
        };


        var currentPage = $location.absUrl();
        if (currentPage.slice(-1) === "/") {
            currentPage = currentPage + "home";
        }

        currentPage += "/1";

        $http({
            method: 'POST',
            url: currentPage,
            data: loginData
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            $scope.loginError = true;
        });

        //Empty list data after process
        $scope.list = [];
    };


}]);


app.controller('RegisterFormSubmitCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {

    $scope.registerError = false;

    $scope.submit = function() {

        var registerData = {
            "email" : $scope.email,
            "firstName" : $scope.firstName,
            "lastName" : $scope.lastName,
            "DoB": $scope.DoB = new Date(),
            "password" : $scope.password
        };

        this.minDate = new Date();
        this.minDate.setFullYear(registerData.DoB.getFullYear()-18);

        $http({
            method: 'POST',
            url: "http://localhost:8080/home",
            data: registerData
        }).then(function successCallback(response) {
            // this callback will be called asynchronously
            // when the response is available
        }, function errorCallback(response) {
            $scope.registerError = true;

        });
    };

}]);

app.controller('DropdownCtrl', function ($scope, $log) {

    $scope.status = {
        isopen: false
    };

    $scope.toggled = function(open) {
        $log.log('Dropdown is now: ', open);
    };

    $scope.toggleDropdown = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.status.isopen = !$scope.status.isopen;
    };

    $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
});




