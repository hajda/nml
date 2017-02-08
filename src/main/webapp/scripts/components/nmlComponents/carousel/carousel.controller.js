(function carousel() {
    'use strict;'

    angular.module('nmlApp')
        .controller('NmlCarouselController', carouselController);

    carouselController.$inject = ['$scope'];

    function carouselController($scope) {


        /**
         * TODO: in app.js import "ui.bootstrap" and "ngRoute"
         * @type {carouselController}
         */

        var vm = this;
        $scope.myInterval = 3000;
        $scope.noWrapSlides = false;
        $scope.active = 0;
        $scope.slides = [
            {
                image: '../../../assets/images/1b.jpg'
            },
            {
                image: '../../../assets/images/2b.jpg'
            },
            {
                image: '../../../assets/images/3.jpg'
            },
            {
                image: '../../../assets/images/4.jpg'
            }
        ];
    }
})();
