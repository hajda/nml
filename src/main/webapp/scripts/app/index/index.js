'use strict';

angular.module('nmlApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('index', {
                parent: 'site',
                url: '/index',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/index/index.html'
                    }
                }//,
                //resolve: {
                //    mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                //        $translatePartialLoader.addPart('main');
                //        return $translate.refresh();
                //    }]
                //}
            });
    });
