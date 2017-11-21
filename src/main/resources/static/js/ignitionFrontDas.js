/**
 *
 */
IGNITION_FRONT_APP.service('ignitionFrontDas', ['$rootScope', '$http', '$q', function ($rootScope, $http, $q) {
    var srv = this;


    srv.getIgnitionServerVersion = function () {
        var deferred = $q.defer();
        var promise = $http.get('/ignition/rest/about');
        promise.then(function (response) {
            var AboutIgnitionDto = response.data;
            deferred.resolve(mapAboutIgnitionDto(AboutIgnitionDto));
        });
        return deferred.promise;
    };


    //
    //
    //

    function mapAboutIgnitionDto(AboutIgnitionDto) {
        return {
            ignitionVersion: AboutIgnitionDto['version']
        }
    }

}]);