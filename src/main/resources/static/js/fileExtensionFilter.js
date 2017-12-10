
//отображает расширение  ////////// <img src="icons/ico_html.png">
myApp.filter('fileExtensionFilter', function () {
    return function (input) {
        if (input.substring(input.indexOf('.')) == ".txt") {
            return "img/icons/ico_txt.png";
        }
        else if (input.substring(input.indexOf('.')) == ".jpg") {
            return "img/icons/ico_jpg.png"
        }
        else if (input.substring(input.indexOf('.')) == ".jpeg") {
            return "img/icons/ico_jpeg.png"
        }
        else if (input.substring(input.indexOf('.')) == ".html") {
            return "img/icons/ico_html.png"
        }
        else if (input.substring(input.indexOf('.')) == ".js") {
            return "img/icons/ico_js.png"
        }
        else if (input.substring(input.indexOf('.')) == ".mp3") {
            return "img/icons/ico_mp3.png"
        }
        else {
            return;
        }

    };
});

