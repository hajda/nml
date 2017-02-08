'use strict';

angular.module('nmlApp')
    .factory('Article', function ($resource, DateUtils) {
        return $resource('api/articles/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.created_at = DateUtils.convertDateTimeFromServer(data.created_at);
                    data.last_modified = DateUtils.convertDateTimeFromServer(data.last_modified);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
