'use strict';

angular.module('nmlApp').directive('nmlMedia', mediaDirective);

function mediaDirective() {
    return {
        restrict: 'AE',
        controller: 'NmlMediaController',
        templateUrl: 'scripts/app/main/media/media.template.html',
        compile: function compile() {
            return {
                pre: function preLink($scope) {
                },
                post: function postLink($scope) {
                    console.log('[media directive]');
                    // $scope.$animationElements = $('.nml-animation-element');
                    // console.log('[media directive] #nml-animation-elements:', $scope.$animationElements.length);
                }
            }
        }
    };
}
