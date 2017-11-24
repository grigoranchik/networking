/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionPlayCtrl', ['$scope', '$timeout', 'ignitionFrontDas', 'ignitionPlayConfig', 'ignitionAvailRecsSrv', function ($scope, $timeout, ignitionFrontDas, ignitionPlayConfig, ignitionAvailRecsSrv) {
    var vm = this;

    vm.currentPlayingRecordingIdx = -1;

    var CURRENT_PLAYING_AUDIO;

    $scope.$watch(function () {
            return ignitionPlayConfig;
        },
        function (newVal, oldVal) {
            $timeout(function () {
                if (CURRENT_PLAYING_AUDIO) {
                    configureVolume(CURRENT_PLAYING_AUDIO);
                }
            });

        }, true);

    vm.onPlayAllAvailableSongs = function () {
        vm.onPlayNextRecording();
    };

    vm.onPlayNextRecording = function () {
        var idx = vm.currentPlayingRecordingIdx;
        var nextIdx = (idx + 1) < ignitionAvailRecsSrv.availableMappedSongsList.length ? idx + 1 : 0;
        vm.currentPlayingRecordingIdx = nextIdx;

        var songByIdx = ignitionAvailRecsSrv.availableMappedSongsList[vm.currentPlayingRecordingIdx];
        var audio = playSongFragment(songByIdx.ignitionAvailSongId);

        audio.addEventListener('playing', function () {
            setTimeout(function () {
                vm.onPlayNextRecording();
            }, ignitionPlayConfig.getIgnitionCfgSnippedLengthMs());
        }, true);

        /*audio.addEventListener('ended', function () {
            vm.onPlayNextRecording();
        }, true);*/
    };

    ignitionAvailRecsSrv.initAvailRecsSrv();

    //
    //
    //

    function playSongFragment(songId) {

        var uri = '/ignition/rest/play/' + songId;

        var audio = new Audio();
        configureVolume(audio);
        audio.loop = false;
        audio.src = uri;
        audio.play();

        audio.addEventListener('playing', function () {
            if (CURRENT_PLAYING_AUDIO) {
                CURRENT_PLAYING_AUDIO.pause();
            }

            CURRENT_PLAYING_AUDIO = audio;
        }, true);

        console.info("Playing recording: " + songId);

        return audio;
    }

    function configureVolume(audio) {
        audio.volume = ignitionPlayConfig.getIgnitionCfgPlaybackVolume();
    }

}]);