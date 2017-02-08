'use strict';

angular.module('nmlApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('paragraph', {
                parent: 'entity',
                url: '/paragraphs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nmlApp.paragraph.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/paragraph/paragraphs.html',
                        controller: 'ParagraphController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('paragraph');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('paragraph.detail', {
                parent: 'entity',
                url: '/paragraph/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'nmlApp.paragraph.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/paragraph/paragraph-detail.html',
                        controller: 'ParagraphDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('paragraph');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Paragraph', function($stateParams, Paragraph) {
                        return Paragraph.get({id : $stateParams.id});
                    }]
                }
            })
            .state('paragraph.new', {
                parent: 'paragraph',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paragraph/paragraph-dialog.html',
                        controller: 'ParagraphDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    text: null,
                                    id: null
                                };
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('paragraph', null, { reload: true });
                    }, function() {
                        $state.go('paragraph');
                    })
                }]
            })
            .state('paragraph.edit', {
                parent: 'paragraph',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paragraph/paragraph-dialog.html',
                        controller: 'ParagraphDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Paragraph', function(Paragraph) {
                                return Paragraph.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('paragraph', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            })
            .state('paragraph.delete', {
                parent: 'paragraph',
                url: '/{id}/delete',
                data: {
                    authorities: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'scripts/app/entities/paragraph/paragraph-delete-dialog.html',
                        controller: 'ParagraphDeleteController',
                        size: 'md',
                        resolve: {
                            entity: ['Paragraph', function(Paragraph) {
                                return Paragraph.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('paragraph', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
