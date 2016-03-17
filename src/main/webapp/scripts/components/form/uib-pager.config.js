'use strict';

angular.module('nmlApp')
    .config(function (uibPagerConfig) {
        uibPagerConfig.itemsPerPage = 20;
        uibPagerConfig.previousText = '«';
        uibPagerConfig.nextText = '»';
    });
