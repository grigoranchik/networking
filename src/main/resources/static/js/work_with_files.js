var myApp = angular.module('myApp', []);

myApp.controller('mainCtrl', ['$http', '$scope', 'serviceForGetListOfFiles', function ($http, $scope, serviceForGetListOfFiles,myDeleteFileDirective) {
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


    $scope.availableFilesListObj = serviceForGetListOfFiles.serviceForGetListObj;
    $scope.$emit('myFilesListNeedsReloading');





}]);
