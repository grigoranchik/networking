/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionFrontMainCtrl', ['$scope', '$timeout', 'ignitionFrontDas', function ($scope, $timeout, ignitionFrontDas) {
    var vm = this;

    vm.availableMappedSongsList = [];
    vm.currentPlayingRecordingIdx = -1;

    vm.initIgnitionFront = function () {
        init();
    };

    vm.onPlaySelectedSong = function (song) {
        var songId = song.ignitionAvailSongId;
        playSongFragment(songId);
    };

    vm.onPlayAllAvailableSongs = function () {
        startPlayingSongsFromIndex(0);
    };

    vm.onPlayNextRecording = function () {
        var idx = vm.currentPlayingRecordingIdx;
        var nextIdx = (idx + 1) < vm.availableMappedSongsList.length ? idx + 1 : 0;
        startPlayingSongsFromIndex(nextIdx);
    };

    //
    //
    //

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

    function startPlayingSongsFromIndex(idx) {

        vm.currentPlayingRecordingIdx = idx;

        var firstSong = vm.availableMappedSongsList[idx];

        var audio = playSongFragment(firstSong.ignitionAvailSongId);
        audio.addEventListener('ended', function () {
            var nextIdx = (idx + 1) < vm.availableMappedSongsList.length ? idx + 1 : 0;
            startPlayingSongsFromIndex(nextIdx);
        }, true);
    }

    function init() {
        ignitionFrontDas.getIgnitionServerVersion().then(function (AboutIgnitionDto) {
            var mappedAboutInfo = ignitionFrontDas.mapAboutIgnitionDto(AboutIgnitionDto);
            console.info("Server version: " + mappedAboutInfo.ignitionVersion);
        });


        ignitionFrontDas.getIgnitionList().then(function (AvailSongDto) {
            vm.availableMappedSongsList = ignitionFrontDas.mapGetAvailSongsDto(AvailSongDto);
            console.info("Found: " + vm.availableMappedSongsList.length + " songs.");
        });
    }

}]);