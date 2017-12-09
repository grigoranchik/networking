//директива по использов. ф-ции на отправку запроса на список файлов, удаления файлов, и вывода их ввиде списка ссылок
/**
 *
 */
myApp.directive('myDeleteFileDirective', ['$http', function ($http) {
    return {
        link: function (scope, element, attrs) {

            //отправить запрос на удаление файла
            scope.setDelete = function (elem) {

                if (confirm("Вы действительно хотите удалить файл?")) {

                    scope.$emit('myFilesListChangingEvent', true);

                    //debugger;
                    var promise = $http.post('http://176.36.229.152:80/ignition/rest/files/delete/' + elem.availFileName, {});
                    promise.then(function (response) {
                        scope.$emit('myFilesListNeedsReloading');

                        scope.$emit('myFilesListChangingEvent', false);

                    }).catch(function (error) {
                        scope.$emit('myFilesListNeedsReloading');
                        scope.$emit('myFilesListChangingEvent', false);
                    });

                } else {
                    alert("нехуй нажимать не подумав!")
                }

            };
        }
    }
}]);
