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

                    var promise = $http.post('/networking/rest/files/delete', {
                        removeNaHuyFileName: elem.availFileName
                    });
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
