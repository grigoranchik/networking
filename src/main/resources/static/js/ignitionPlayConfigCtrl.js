/**
 *
 */
IGNITION_FRONT_APP.value('ignitionPlayConfig', {
    ignitionCfgSnippetLength: 0,
    ignitionCfgSnippetsNum: 0,
    ignitionCfgVolumePercent: 0
});

/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionPlayConfigCtrl', ['$scope', '$timeout', 'ignitionFrontDas', 'ignitionPlayConfig', function ($scope, $timeout, ignitionFrontDas, ignitionPlayConfig) {
    var vm = this;

    var ValidationConst = {
        MIN_SNIPPET_LENGTH_SEC: 1,
        MAX_SNIPPET_LENGTH_SEC: 5,

        MIN_SNIPPETS_NUM: 1,
        MAX_SNIPPETS_NUM: 3,

        MIN_VOLUME_PERCENT: 0,
        MAX_VOLUME_PERCENT: 100,
    };

    vm.playbackConfig = {
        playbackSnippetLengthSec: 3,
        playbackOneRecSnippetsNum: 1,
        playbackVolumePercent: 30
    };

    $scope.$watch(function () {
            return vm.playbackConfig;
        },
        function (newVal, oldVal) {
            $timeout(function () {
                
                validateConfig(newVal);

                ignitionPlayConfig.ignitionCfgSnippetLength = vm.playbackConfig.playbackSnippetLengthSec;
                ignitionPlayConfig.ignitionCfgSnippetsNum = vm.playbackConfig.playbackOneRecSnippetsNum;
                ignitionPlayConfig.ignitionCfgVolumePercent = vm.playbackConfig.playbackVolumePercent;
            });

        }, true);

    //
    //
    //

    function validateConfig(newVal) {

        if (newVal.playbackSnippetLengthSec < ValidationConst.MIN_SNIPPET_LENGTH_SEC) {
            vm.playbackConfig.playbackSnippetLengthSec = ValidationConst.MIN_SNIPPET_LENGTH_SEC;
        } else if (newVal.playbackSnippetLengthSec > ValidationConst.MAX_SNIPPET_LENGTH_SEC) {
            vm.playbackConfig.playbackSnippetLengthSec = ValidationConst.MAX_SNIPPET_LENGTH_SEC;
        }

        if (newVal.playbackOneRecSnippetsNum < ValidationConst.MIN_SNIPPETS_NUM) {
            vm.playbackConfig.playbackOneRecSnippetsNum = ValidationConst.MIN_SNIPPETS_NUM;
        } else if (newVal.playbackOneRecSnippetsNum > ValidationConst.MAX_SNIPPETS_NUM) {
            vm.playbackConfig.playbackOneRecSnippetsNum = ValidationConst.MAX_SNIPPETS_NUM;
        }

        if (newVal.playbackVolumePercent < ValidationConst.MIN_VOLUME_PERCENT) {
            vm.playbackConfig.playbackVolumePercent = ValidationConst.MIN_VOLUME_PERCENT;
        } else if (newVal.playbackVolumePercent > ValidationConst.MAX_VOLUME_PERCENT) {
            vm.playbackConfig.playbackVolumePercent = ValidationConst.MAX_VOLUME_PERCENT;
        }
    }
}]);