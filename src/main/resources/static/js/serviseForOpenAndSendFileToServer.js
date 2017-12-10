

myApp.service('serviseForOpenAndSendFileToServer',['$rootScope','$http','serviceForGetListOfFiles', function($rootScope, $http, serviceForGetListOfFiles) {
    this.myFunc = function (file) {

            //$scope.$emit('myFilesListNeedsReloading');
            var files = file;
            $rootScope.$broadcast('myFilesListChangingEvent', true);
            $rootScope.availableFilesListObj = serviceForGetListOfFiles.serviceForGetListObj;
            var data = new FormData();
            var Ident_file = false;
            data.append('file', files[0]);
            debugger;
            for (var i = 0; i < $rootScope.availableFilesListObj.listOfFiles.length; i++) {
                if ($rootScope.availableFilesListObj.listOfFiles[i].availFileName == files[0].name) {
                    alert('файл с таким именем уже существует');
                    Ident_file = true;
                    return;
                }
            }
            ;
            if (Ident_file == true) {
                return;
            }

            debugger;
            var promise = $http.post('http://localhost:8090/networking/rest/files/upload', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            });
            promise.then(function () {
                $rootScope.$broadcast('myFilesListNeedsReloading');
                $rootScope.$broadcast('myFilesListChangingEvent', false);
            }).catch(function (error) {
                console.log(error.status);
                $rootScope.$broadcast('myFilesListChangingEvent', false);
            });
        };
        return ;

}]);