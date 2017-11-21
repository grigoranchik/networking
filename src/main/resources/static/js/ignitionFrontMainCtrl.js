/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionFrontMainCtrl', ['$scope', '$timeout', 'ignitionFrontDas', function ($scope, $timeout, ignitionFrontDas) {
    var vm = this;

    vm.availableMappedSongsList = [];

    vm.initIgnitionFront = function () {
        init();
    };

    vm.onPlaySelectedSong = function (song) {
        var songId = song.ignitionAvailSongId;

        /*ignitionFrontDas.getSongFragment(songId).then(function (bytes) {
            console.info("Downloaded: " + bytes.length + " for song " + songId);
        });*/

        var audio = ignitionFrontDas.playSongFragment(songId);
    };

    vm.onPlayAllAvailableSongs = function () {
        startPlayingSongsFromIndex(0);
    };


    function startPlayingSongsFromIndex(idx) {
        var firstSong = vm.availableMappedSongsList[idx];

        var audio = ignitionFrontDas.playSongFragment(firstSong.ignitionAvailSongId);
        audio.addEventListener('ended', function () {
            var nextIdx = (idx + 1) < vm.availableMappedSongsList.length ? idx + 1 : 0;
            startPlayingSongsFromIndex(nextIdx);
        }, true);
    }

    //
    //
    //

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