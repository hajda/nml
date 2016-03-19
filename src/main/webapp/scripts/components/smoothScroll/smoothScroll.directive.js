/**
 * Created by PD on 2016.03.11..
 */
angular.module('nmlApp').directive('nmlSmoothScroll', smoothScrollDirective);

smoothScrollDirective.$inject = [];

function smoothScrollDirective() {
    return {
        restrict: 'AE',
        controller: 'NmlSmoothScrollController',
        link: function link($scope) {
            $scope.scrollDelta = 'small-delta';  // initially, the scroll delta is 0, thus small
        }
    }
}
