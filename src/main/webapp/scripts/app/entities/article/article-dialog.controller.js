'use strict';

angular.module('nmlApp').controller('ArticleDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Article', 'User',
        function($scope, $stateParams, $uibModalInstance, entity, Article, User) {

        $scope.article = entity;
        $scope.users = User.query();
        $scope.load = function(id) {
            Article.get({id : id}, function(result) {
                $scope.article = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('nmlApp:articleUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.article.id != null) {
                Article.update($scope.article, onSaveSuccess, onSaveError);
            } else {
                Article.save($scope.article, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.datePickerForCreated_at = {};

        $scope.datePickerForCreated_at.status = {
            opened: false
        };

        $scope.datePickerForCreated_atOpen = function($event) {
            $scope.datePickerForCreated_at.status.opened = true;
        };
        $scope.datePickerForLast_modified = {};

        $scope.datePickerForLast_modified.status = {
            opened: false
        };

        $scope.datePickerForLast_modifiedOpen = function($event) {
            $scope.datePickerForLast_modified.status.opened = true;
        };
}]);
