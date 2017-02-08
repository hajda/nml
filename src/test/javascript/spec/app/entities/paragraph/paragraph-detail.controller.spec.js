'use strict';

describe('Controller Tests', function() {

    describe('Paragraph Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockParagraph, MockSection;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockParagraph = jasmine.createSpy('MockParagraph');
            MockSection = jasmine.createSpy('MockSection');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Paragraph': MockParagraph,
                'Section': MockSection
            };
            createController = function() {
                $injector.get('$controller')("ParagraphDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'nmlApp:paragraphUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
