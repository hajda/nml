'use strict';

angular.module('nmlApp')
    .controller('SectionDetailController', function ($scope, $rootScope, $stateParams, entity, Section) {
        $scope.section = entity;
        $scope.load = function (id) {
            Section.get({id: id}, function(result) {
                $scope.section = result;
            });
        };
        var unsubscribe = $rootScope.$on('nmlApp:sectionUpdate', function(event, result) {
            $scope.section = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
