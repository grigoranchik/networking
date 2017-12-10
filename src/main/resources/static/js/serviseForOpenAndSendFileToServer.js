myApp.service('serviseForOpenAndSendFileToServer', function() {
    this.myFunc = function (x) {
        // //онклик на загрузку и отправку файл на сервер
        $('input[type=file]').on('change', function () {
            //$scope.$emit('myFilesListNeedsReloading');
            files = this.files;
            $scope.$emit('myFilesListChangingEvent', true);
            var data = new FormData();
            var Ident_file = false;
            data.append('file', files[0]);

            for (var i = 0; i < $scope.availableFilesListObj.listOfFiles.length; i++) {
                if ($scope.availableFilesListObj.listOfFiles[i].availFileName == files[0].name) {
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
                $scope.$emit('myFilesListNeedsReloading');
                $scope.$emit('myFilesListChangingEvent', false);
            }).catch(function (error) {
                console.log(error.status);
                $scope.$emit('myFilesListChangingEvent', false);
            });
        });
        return x.toString(16);
    }
});