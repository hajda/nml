/**
 * Created by PD on 2016.03.08..
 */
angular.module('nmlApp').controller('NmlNavController', navController);

navController.$inject = ['$scope', '$window', '$location', 'NmlSmoothScroll'];

function navController($scope, $window, $location, NmlSmoothScroll) {

    $scope.toggleMenu = function() {
        $scope.menuVisibility = !$scope.menuVisibility;
    };

    $scope.hideMenu = function() {
        // if (!isDesktopView()) {
        $scope.menuVisibility = false;
        // }
    };

    $scope.onMenuItemClick = function($event, pageName, marker) {
        $scope.hideMenu();
        $scope.scrollTo(marker);
        $scope.pageName = pageName;
        console.log('page name:', $scope.pageName);
    };

    /**
     * // return document.body.clientWidth;
     * // return $window.screen.width;
     * @returns {number}
     */
    function getViewportSize() {
        return document.documentElement.clientWidth;
    }

    function isDesktopView() {
        return getViewportSize() > 703;
    }

    $scope.menuVisibility = false;//isDesktopView();
}


// define and register onresize
$window.onresize = function() {
    // $scope.viewportWidth = getViewportSize();
    // $scope.menuVisibility = isDesktopView();
    // $scope.$apply();
}

// define, register and trigger onOrientationChange
function onOrientationChange() {
    // console.log('device orientation: ' + $window.orientation + '°');
    // $scope.orientation = $window.orientation;
    // switch ($window.orientation) {
    //     case -90:
    //     case 90:
    //         $scope.menuVisibility = false;
    //         break;
    //     default:
    //         $scope.menuVisibility = true;
    //         break;
    // }
}
$window.addEventListener('orientationchange', onOrientationChange);
onOrientationChange();
