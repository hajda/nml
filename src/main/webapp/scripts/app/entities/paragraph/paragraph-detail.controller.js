'use strict';

angular.module('nmlApp')
    .controller('ParagraphDetailController', function ($scope, $rootScope, $stateParams, entity, Paragraph, Section) {
        $scope.paragraph = entity;
        $scope.load = function (id) {
            Paragraph.get({id: id}, function(result) {
                $scope.paragraph = result;
            });
        };
        var unsubscribe = $rootScope.$on('nmlApp:paragraphUpdate', function(event, result) {
            $scope.paragraph = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
