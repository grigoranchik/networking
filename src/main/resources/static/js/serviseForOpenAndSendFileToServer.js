myApp.service('serviseForOpenAndSendFileToServer',['$rootScope','$http', function($rootScope, $http) {
    this.myFunc = function (x) {
        // //онклик на загрузку и отправку файл на сервер
        $('input[type=file]').on('change', function () {
            //$scope.$emit('myFilesListNeedsReloading');
            var files = this.files;
            $rootScope.$emit('myFilesListChangingEvent', true);
            var data = new FormData();
            var Ident_file = false;
            data.append('file', files[0]);

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
            var promise = $http.post('/networking/rest/files/upload', data, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            });
            promise.then(function () {
                $rootScope.$emit('myFilesListNeedsReloading');
                $rootScope.$emit('myFilesListChangingEvent', false);
            }).catch(function (error) {
                console.log(error.status);
                $rootScope.$emit('myFilesListChangingEvent', false);
            });
        });
        return ;
    }
}]);