'use strict';

angular.module('nmlApp').controller('SectionDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Section',
        function($scope, $stateParams, $uibModalInstance, entity, Section) {

        $scope.section = entity;
        $scope.load = function(id) {
            Section.get({id : id}, function(result) {
                $scope.section = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('nmlApp:sectionUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.section.id != null) {
                Section.update($scope.section, onSaveSuccess, onSaveError);
            } else {
                Section.save($scope.section, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
