/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionPlayCtrl', ['$scope', '$timeout', 'ignitionFrontDas', 'ignitionPlayConfig', 'ignitionAvailRecsSrv', function ($scope, $timeout, ignitionFrontDas, ignitionPlayConfig, ignitionAvailRecsSrv) {
    var vm = this;

    vm.currentPlayingRecordingIdx = -1;

    vm.onPlayAllAvailableSongs = function () {
        vm.onPlayNextRecording();
        setInterval(function () {
            vm.onPlayNextRecording();
        }, 3000);
    };

    vm.onPlayNextRecording = function () {
        var idx = vm.currentPlayingRecordingIdx;
        var nextIdx = (idx + 1) < ignitionAvailRecsSrv.availableMappedSongsList.length ? idx + 1 : 0;
        vm.currentPlayingRecordingIdx = nextIdx;
        playCurrentRecordingIdx();
    };

    ignitionAvailRecsSrv.initAvailRecsSrv();

    //
    //
    //

    function playCurrentRecordingIdx() {
        var songByIdx = ignitionAvailRecsSrv.availableMappedSongsList[   vm.currentPlayingRecordingIdx ];
        var audio = playSongFragment(songByIdx.ignitionAvailSongId);

        /*audio.addEventListener('ended', function () {
            var nextIdx = (idx + 1) < ignitionAvailRecsSrv.availableMappedSongsList.length ? idx + 1 : 0;
            startPlayingSongsFromIndex(nextIdx);
        }, true);*/
    }

    var AUDIO;

    function playSongFragment(songId) {

        var uri = '/ignition/rest/play/' + songId;

        var audio = new Audio();
        audio.volume = 0.3;
        audio.loop = false;
        audio.src = uri;
        audio.play();

        audio.addEventListener('playing', function () {
            console.info("!!! Started playing: " + songId);

            if (AUDIO) {
                AUDIO.pause();
            }

            AUDIO = audio;

        }, true);

        console.info("Playing recording: " + songId);

        return audio;
    }

}]);