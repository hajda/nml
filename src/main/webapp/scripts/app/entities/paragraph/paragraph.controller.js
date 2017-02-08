'use strict';

angular.module('nmlApp')
    .controller('ParagraphController', function ($scope, $state, Paragraph) {

        $scope.paragraphs = [];
        $scope.loadAll = function() {
            Paragraph.query(function(result) {
               $scope.paragraphs = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.paragraph = {
                text: null,
                id: null
            };
        };
    });
