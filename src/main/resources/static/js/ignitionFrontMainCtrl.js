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
        ignitionFrontDas.getSongFragment(songId).then(function (bytes) {
            console.info("Downloaded: " + bytes.length + " for song " + songId);
        });
    };

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