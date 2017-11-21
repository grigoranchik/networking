/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionFrontMainCtrl', ['$scope', '$timeout', 'ignitionFrontDas', function ($scope, $timeout, ignitionFrontDas) {
    var vm = this;

    vm.initIgnitionFront = function () {
        init();
    };

    //
    //
    //

    function init() {
        ignitionFrontDas.getIgnitionServerVersion().then(function (mappedAboutIgnitionDto) {
            console.info("Server version: " + mappedAboutIgnitionDto.ignitionVersion);
        });
    }

}]);