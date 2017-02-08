/**
 * Created by PD on 2016.03.11..
 */
angular.module('nmlApp').directive('nmlNav', navDirective);

navDirective.$inject = [];

function navDirective() {
    return {
        restrict: 'AE',
        controller: 'NmlNavController',
        templateUrl: 'scripts/components/nmlComponents/nav/nav.template.html'/*,
        link: function link($scope) {
        }*/
    }
}
