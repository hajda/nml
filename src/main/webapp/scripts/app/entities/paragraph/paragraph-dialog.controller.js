'use strict';

angular.module('nmlApp').controller('ParagraphDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Paragraph', 'Section',
        function($scope, $stateParams, $uibModalInstance, entity, Paragraph, Section) {

        $scope.paragraph = entity;
        $scope.sections = Section.query();
        $scope.load = function(id) {
            Paragraph.get({id : id}, function(result) {
                $scope.paragraph = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('nmlApp:paragraphUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.paragraph.id != null) {
                Paragraph.update($scope.paragraph, onSaveSuccess, onSaveError);
            } else {
                Paragraph.save($scope.paragraph, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
