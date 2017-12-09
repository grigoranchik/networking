/**
 * serviceForGetListOfFiles.getFilesListFromServer();
 * serviceForGetListOfFiles.serviceForGetListObj.listOfFiles;
 */
myApp.service('serviceForGetListOfFiles', ['$timeout', '$q', '$http', '$rootScope', function ($timeout, $q, $http, $rootScope) {

    var srv = this;

    srv.serviceForGetListObj = {
        listOfFiles: []
    };


    $rootScope.$on('myFilesListNeedsReloading', function (event, data) {
        srv.getFilesListFromServer();
    });

    srv.getFilesListFromServer = function () {
        var deferred = $q.defer();

        //$rootScope.$bradcast('myFilesListChangingEvent_Shown_Text', true);


        var promise = $http.get('/networking/rest/files/list');
        promise.then(function (response) {
            srv.serviceForGetListObj.listOfFiles = response.data.availableFilesList;
            deferred.resolve(srv.serviceForGetListObj.listOfFiles);
            if (srv.serviceForGetListObj.listOfFiles==[0]){//почему из сервиса нельзя отправить эмит?
                //debugger;
                $rootScope.$bradcast('myFilesListChangingEvent_Shown_Text', true);
            }

        }).catch(function (error) {
            console.log(error.status);
            deferred.resolve(srv.serviceForGetListObj.listOfFiles);
        });

        return deferred.promise;
    };

}]);

