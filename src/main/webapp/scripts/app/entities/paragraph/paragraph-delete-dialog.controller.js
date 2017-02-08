'use strict';

angular.module('nmlApp')
	.controller('ParagraphDeleteController', function($scope, $uibModalInstance, entity, Paragraph) {

        $scope.paragraph = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Paragraph.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
