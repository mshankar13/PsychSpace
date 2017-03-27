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

app.controller('LoginFormSubmitCtrl', ['$scope', '$http', function($scope, $http) {

    $scope.list = [];

    $scope.headerText = 'AngularJS Post Form Spring MVC example: Submit below form';
    $scope.submit = function() {

        var loginData = {
            "email" : $scope.email,
            "password" : $scope.password
        };

        var response = $http.post('login', loginData);
        response.success(function(data, status, headers, config) {
            $scope.list.push(data);
        });
        response.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });

        //Empty list data after process
        $scope.list = [];
    };


}]);


app.controller('RegisterFormSubmitCtrl', ['$scope', '$http', function($scope, $http) {

    $scope.submit = function() {

        var registerData = {
            "email" : $scope.email,
            "firstName" : $scope.firstName,
            "lastName" : $scope.lastName,
            "DoB": $scope.DoB,
            "password" : $scope.password
        };

        this.minDate = new Date(
            registerData.DoB.getFullYear() - 18,
            registerData.DoB.getMonth(),
            registerData.DoB.getDate()
        );

        console.log(this.minDate);

        var response = $http.post('register', registerData);
        response.success(function(data, status, headers, config) {
            $scope.list.push(data);
        });
        response.error(function(data, status, headers, config) {
            alert( "Exception details: " + JSON.stringify({data: data}));
        });

        //Empty list data after process
        $scope.list = [];
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




