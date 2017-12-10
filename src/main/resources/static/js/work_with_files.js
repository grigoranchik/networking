var myApp = angular.module('myApp', []);

myApp.controller('mainCtrl', ['$http', '$scope', 'serviceForGetListOfFiles','serviseForOpenAndSendFileToServer', function ($http, $scope, serviceForGetListOfFiles, serviseForOpenAndSendFileToServer) {
    var files;
    $scope.ng_model_value_of_text_from_searching;

    $scope.isAjaxLoaderShown = false;
    $scope.isAjaxLoaderShown_Text_null = false;
    // Слуханим ивенти
    $scope.$on('myFilesListChangingEvent', function (event, data) {
        $scope.isAjaxLoaderShown = data;
    });

    // debugger;
    $scope.$on('myFilesListChangingEvent_Shown_Text', function (event, data) {
        //debugger;
        $scope.isAjaxLoaderShown_Text_null = data;
    });



    //онклик на загрузку и отправку файл на сервер
    $('input[type=file]').on('change', function () {
        serviseForOpenAndSendFileToServer.myFunc(this.files);
    })

    $scope.$emit('myFilesListNeedsReloading');


}]);
