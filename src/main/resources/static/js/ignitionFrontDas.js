/**
 *
 */
IGNITION_FRONT_APP.service('ignitionFrontDas', ['$rootScope', '$http', '$q', function ($rootScope, $http, $q) {
    var srv = this;


    srv.getIgnitionServerVersion = function () {
        return restDoGet('/ignition/rest/about', function (deferred, AboutIgnitionDto) {
            deferred.resolve(AboutIgnitionDto);
        });
    };

    srv.mapAboutIgnitionDto = function (AboutIgnitionDto) {
        return {
            ignitionVersion: AboutIgnitionDto['version']
        }
    };

    srv.getIgnitionList = function () {
        return restDoGet('/ignition/rest/list', function (deferred, GetAvailSongsDto) {
            deferred.resolve(GetAvailSongsDto);
        });
    };

    srv.mapGetAvailSongsDto = function (GetAvailSongsDto) {
        return _.map(GetAvailSongsDto['availableSongsList'], function (AvailSongDto) {
            return srv.mapAvailSongDto(AvailSongDto);
        })
    };

    srv.mapAvailSongDto = function (AvailSongDto) {
        return {
            ignitionAvailSongId: AvailSongDto['availSongId']
        }
    };

    srv.getSongFragment = function (songId) {
        return restDoGet('/ignition/rest/play/' + songId, function (deferred, bytes) {
            deferred.resolve(bytes);
        });
    };

    var AUDIO;
    srv.playSongFragment = function (songId) {

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
    };

    //
    //
    //

    function restDoGet(uri, resolveCallBack) {
        var deferred = $q.defer();
        var promise = $http.get(uri);
        promise.then(function (response) {
            resolveCallBack(deferred, response.data)
        });
        return deferred.promise;
    }

}]);