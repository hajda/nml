'use strict';

angular.module('nmlApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


