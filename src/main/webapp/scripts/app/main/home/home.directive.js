/**
 * Created by PD on 2016.03.11..
 */
angular.module('nmlApp').directive('nmlHome', homeDirective);

homeDirective.$inject = [];

function homeDirective() {
    return {
        restrict: 'AE',
        templateUrl: 'scripts/app/main/home/home.template.html'
    }
}
