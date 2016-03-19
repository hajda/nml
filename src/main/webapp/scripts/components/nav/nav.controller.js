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
        if (!isDesktopView()) {
            $scope.menuVisibility = false;
        }
    };

    $scope.onMenuItemClick = function() {
        $scope.hideMenu();
    };

    function getViewportSize() {
        //return document.body.clientWidth;
        return document.documentElement.clientWidth;
        //return $window.screen.width;
    }

    function isDesktopView() {
        return getViewportSize() > 720;
    }

    // //////////////// observing orientation and resize ///////////////////////////////////

    $window.onresize = function() {

        $scope.viewportWidth = getViewportSize();
        $scope.menuVisibility = isDesktopView();
        $scope.$apply();
    }

    function onOrientationChange() {
        console.log('device orientation: ' + $window.orientation + '°');
        $scope.orientation = $window.orientation;
        switch ($window.orientation) {
            case -90:
            case 90:
                $scope.menuVisibility = false;
                break;
            default:
                $scope.menuVisibility = true;
                break;
        }
    }
    $window.addEventListener('orientationchange', onOrientationChange);

    // invoking events if necessary
    onOrientationChange();
    $scope.menuVisibility = isDesktopView();
}
