(function carousel() {
    'use strict;'
    angular.module('nmlApp')
        .directive('nmlCarousel', carouselDirective);

    carouselDirective.$inject = [];

    function carouselDirective() {
        return {
            restrict: 'AE',
            templateUrl: 'scripts/components/nmlComponents/carousel/carousel.template.html',
            controller: 'NmlCarouselController',
            controllerAs: 'vm',
            link: function link($scope, elementInstance, attributeInstances, controller) {

            }
        };
    }
})();
