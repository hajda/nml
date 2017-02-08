'use strict';

angular.module('nmlApp')
    .factory('Paragraph', function ($resource, DateUtils) {
        return $resource('api/paragraphs/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
