(function newsPage() {
    'use strict;'
    angular.module('nmlApp')
        .directive('nmlNewsPage', newsPageDirective);

    newsPageDirective.$inject = [];

    function newsPageDirective() {
        return {
            restrict: 'AE',
            templateUrl: 'scripts/app/main/newsPage/newsPage.template.html',
            controller: 'NmlNewsPageController',
            controllerAs: 'vm',
            link: function link($scope, elementInstance, attributeInstances, controller) {

            }
        };
    }
})();
