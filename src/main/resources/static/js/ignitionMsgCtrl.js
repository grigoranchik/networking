/**
 *
 */
IGNITION_FRONT_APP.controller('ignitionMsgCtrl', ['$scope', '$timeout', '$http', '$q', function ($scope, $timeout, $http, $q) {
    var vm = this;

    $scope.newMessageText = null;

    $scope.onSendNewMessageClicked = function () {
        sendMessageToChat(  $scope.newMessageText )
    };

    //
    //
    //

    function getChatMesssages() {
        var promise = $http.get('https://176.36.229.152/ignition/rest/messages/list');
        promise.then(function (response) {
            console.info("All messages: " + response.data);
        });
    }

    function sendMessageToChat(messageText) {
        var promise = $http.post('https://176.36.229.152/ignition/rest/messages/send');
        promise.then(function (response) {
            console.info("Message sending response: " + response.data);
        });
    }

    debugger;

}]);