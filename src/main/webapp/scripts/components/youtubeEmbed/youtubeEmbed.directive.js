(function youtubeEmbed() {
    'use strict;'
    angular.module('nmlApp')
        .directive('nmlYoutubeEmbed', youtubeEmbedDirective);

    youtubeEmbedDirective.$inject = [];

    function youtubeEmbedDirective() {
        return {
            restrict: 'AE',
            templateUrl: 'scripts/components/youtubeEmbed/youtubeEmbed.template.html',
            controller: 'NmlYoutubeEmbedController',
            controllerAs: 'vm',
            link: function link($scope, elementInstance, attributeInstances, controller) {

            }
        };
    }
})();
