myApp.service('serviseForRename_files', function() {
    this.myFunc = function (x) {

        //функция для смены имени файла
        $scope.link = function (fileObject) {
            var fileName = fileObject.availFileName;

            //var a=$(this).attr("name");
            console.log(fileName);

            bootbox.prompt({
                size: "small",
                title: "хотите поменять имя файла?",
                callback: function(result){
                    if (result==null){return}
                    else{
                        //$(this).attr("name")
                        myDeleteFileDirective.setDelete(fileObject.availFileName);

                    }
                }
            })
        };

        return x.toString(16);
    }
});