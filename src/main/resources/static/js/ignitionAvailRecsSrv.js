/**
 *
 */
IGNITION_FRONT_APP.service('ignitionAvailRecsSrv', ['$rootScope', '$http', '$q', 'ignitionFrontDas', function ($rootScope, $http, $q, ignitionFrontDas) {

    var srv = this;

    srv.availableMappedSongsList = [];

    srv.initAvailRecsSrv = function () {
        loadAvalRecs();
    };


    //
    //
    //

    function loadAvalRecs() {
        ignitionFrontDas.getIgnitionList().then(function (AvailSongDto) {
            srv.availableMappedSongsList = ignitionFrontDas.mapGetAvailSongsDto(AvailSongDto);
            console.info("Found: " + srv.availableMappedSongsList.length + " songs.");

            /*_.forEach(vm.availableMappedSongsList, function (song, i) {
                preCache(song);
            });*/
        });
    }

    function preCache(song) {
        var songId = song.ignitionAvailSongId;
        var uri = '/ignition/rest/play/' + songId;
        var audio = new Audio();
        audio.src = uri;
    }
}]);