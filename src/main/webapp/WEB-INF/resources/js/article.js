/**
 * Created by acerini on 3/27/2017.
 */
var app = angular.module('newsdetApp', ['ngMaterial', 'ngAnimate', 'ngSanitize', 'ui.bootstrap']);
app.controller('Article', function($scope) {
    $scope.title = [];
    $scope.loadData = function(title)
    {
        $scope.title = JSON.parse(title);
    };
});