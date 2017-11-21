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