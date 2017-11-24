/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionPlayConfigCtrl', ['$scope', '$timeout', 'ignitionFrontDas', function ($scope, $timeout, ignitionFrontDas) {
    var vm = this;

    vm.playbackConfig = {
        playbackSnippetLengthSec: 3,
        playbackOneRecSnippetsNum: 1,
        playbackVolumePercent: 30
    };

}]);