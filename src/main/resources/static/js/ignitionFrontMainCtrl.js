/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionFrontMainCtrl', ['$scope', '$timeout', 'ignitionFrontDas', function ($scope, $timeout, ignitionFrontDas) {
    var vm = this;

    vm.sayIgnitionHello = function () {
        console.info("Hello..");
    }

}]);