/**
 * Created by PD on 2016.03.11..
 */
angular.module('nmlApp').directive('nmlSmoothScroll', smoothScrollDirective);

smoothScrollDirective.$inject = ['NmlSmoothScroll'];

function smoothScrollDirective(NmlSmoothScroll) {
    return {
        restrict: 'AE',
        controller: 'NmlSmoothScrollController',
        compile: function compile() {
            return {
                pre: function preLink($scope) {
                    $scope.scrollDelta = 'small-delta';  // initially, the scroll delta is 0, thus small
                },
                post: function postLink($scope) {
                    console.log('[smooth-scroll directive]');

                    $scope.$animationElements = $('.nml-animation-element');
                    console.log('[smooth-scroll directive] #nml-animation-elements:', $scope.$animationElements);
                    $scope.$$window = $(window);

                    $scope.$$window.on('scroll resize', function $checkIfInView() {
                        collectAnimationElements();
                        console.log('[SmoothScroll controller] $checkIfInView()')
                        var window_height = $scope.$$window.height();
                        var window_top_position = $scope.$$window.scrollTop();
                        var window_bottom_position = (window_top_position + window_height);

                        console.log('num of animation elements:', $scope.$animationElements.length, $scope.$animationElements);
                        $.each($scope.$animationElements, function() {
                            console.log('iteration');
                            var $element = $(this);
                            var element_height = $element.outerHeight();
                            var element_top_position = $element.offset().top;
                            var element_bottom_position = (element_top_position + element_height);

                            //check to see if this current container is within viewport
                            if ((element_bottom_position >= window_top_position) &&
                                (element_top_position <= window_bottom_position)) {
                                console.log('add class to rlrmrnt >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>');
                                $element.addClass('in-view');
                            } else {
                                // $element.removeClass('in-view');
                            }
                        });
                    });

                    function collectAnimationElements() {
                        console.log(':) :) :) :) :) :) :) :) :) :) :) :) :)')
                        if ($scope.$animationElements.length == 0) {
                            console.log('animation elements have NOT been collected')
                            $scope.$animationElements = $('.nml-animation-element');
                        }
                        if ($scope.$animationElements.length != 0) {
                            console.log('animation elements have been collected')
                            collectAnimationElements = function() {}
                        }
                    }
                    $scope.$$window.trigger('scroll');
                }
            }
        }
    }
}
