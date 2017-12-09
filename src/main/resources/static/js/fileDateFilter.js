
//конвертирует дату с милисекунд на заданный формат, проверяет сегоднешняя она, вчерашняя или раньше..
myApp.filter('fileDateFilter', function () {
    return function (input) {
        var dt = new Date();
        var month = dt.getMonth() + 1;
        if (month < 10) month = '0' + month;
        var day = dt.getDate();
        if (day < 10) day = '0' + day;
        var year = dt.getFullYear();
        //console.log(day+'.'+month+'.'+year);
        if (new Date(input).toString('dd.MM.yyyy') == day + '.' + month + '.' + year) {
            return ('today' + new Date(input).toString(' HH:mm'));
        }
        else if (new Date(input).toString('dd.MM.yyyy') == parseInt(day.substring(1)) + 1 + '.' + month + '.' + year) {
            return ('tomorrow' + new Date(input).toString(' HH:mm'));
        }
        else
            return new Date(input).toString('dd.MM.yyyy HH:mm');
    };
});
