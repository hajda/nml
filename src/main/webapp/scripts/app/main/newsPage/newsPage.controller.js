(function newsPage() {
    'use strict;'

    angular.module('nmlApp')
        .controller('NmlNewsPageController', newsPageController);

    newsPageController.$inject = [];

    function newsPageController() {
        var vm = this;
    }
})();
