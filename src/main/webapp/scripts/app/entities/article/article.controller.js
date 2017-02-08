'use strict';

angular.module('nmlApp')
    .controller('ArticleController', function ($scope, $state, Article) {

        $scope.articles = [];
        $scope.loadAll = function() {
            Article.query(function(result) {
               $scope.articles = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.article = {
                title: null,
                leadin: null,
                created_at: null,
                last_modified: null,
                id: null
            };
        };
    });
