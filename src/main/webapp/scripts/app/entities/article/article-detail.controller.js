'use strict';

angular.module('nmlApp')
    .controller('ArticleDetailController', function ($scope, $rootScope, $stateParams, entity, Article, User) {
        $scope.article = entity;
        $scope.load = function (id) {
            Article.get({id: id}, function(result) {
                $scope.article = result;
            });
        };
        var unsubscribe = $rootScope.$on('nmlApp:articleUpdate', function(event, result) {
            $scope.article = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
