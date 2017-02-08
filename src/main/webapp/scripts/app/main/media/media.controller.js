'use strict';

angular.module('nmlApp').controller('NmlMediaController', mediaController);

function mediaController($scope) {

    $scope.subMenu = 'photo';
    $scope.selectSubMenu = function selectSubMenu(subMenu) {
        $scope.subMenu = subMenu;
    };

    // $scope.videos = [
    //     {
    //         youtubeSrc: 'www.youtube.com/embed/lHje9w7Ev4U?list=RDr5KtEToyWrI',
    //         caption: 'Video Caption',
    //         description: 'Description about the video.',
    //         width: 420,
    //         height: 315
    //     },
    //     {
    //         youtubeSrc: 'https://www.youtube.com/embed/5a1rZrnUIRg',
    //         caption: 'Video Caption',
    //         description: 'Description about the video.',
    //         width: 420,
    //         height: 315
    //     },
    //     {
    //         youtubeSrc: 'https://www.youtube.com/embed/r5KtEToyWrI',
    //         caption: 'Video Caption',
    //         description: 'Description about the video.',
    //         width: 420,
    //         height: 315
    //     }
    // ];
}
