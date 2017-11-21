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
        ignitionFrontDas.getIgnitionServerVersion().then(function (AboutIgnitionDto) {
            var mappedAboutInfo = ignitionFrontDas.mapAboutIgnitionDto(AboutIgnitionDto);
            console.info("Server version: " + mappedAboutInfo.ignitionVersion);
        });


        ignitionFrontDas.getIgnitionList().then(function (AvailSongDto) {
            var mappedSongsList = ignitionFrontDas.mapGetAvailSongsDto(AvailSongDto);
            console.info("Found: " + mappedSongsList.ignitionAvailSongs.length + " songs.");
        });
    }

}]);