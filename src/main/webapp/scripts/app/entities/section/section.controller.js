'use strict';

angular.module('nmlApp')
    .controller('SectionController', function ($scope, $state, Section) {

        $scope.sections = [];
        $scope.loadAll = function() {
            Section.query(function(result) {
               $scope.sections = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.section = {
                title: null,
                leadin: null,
                id: null
            };
        };
    });
