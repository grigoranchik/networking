
* Install Chrome extension: https://chrome.google.com/webstore/detail/custom-javascript-for-web/poakhlngfciodnhlhhgnaaelnpjljija?hl=en

* Open web console and add filter sc-cjs:




```




function sendDataToServer(sTitle, sUrl, sPlayListUrl){
    
    var requestUrl = "https://localhost/ignition/rest/song/new";

    var callAttachedXhr = $.ajax({
        type: 'POST',
        url: requestUrl,
        async: true,
        data: JSON.stringify({
            scCjsSongTitle: sTitle,
            scCjsSongUrl: sUrl,
            scCjsSongPlayListUrl: sPlayListUrl,
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        beforeSend: function () {
            
        },
        success: function (AbstractResponse) {
            logInfo("Sending succeed");
        },
        error: function (xhr, ajaxOptions, thrownError) {
            logInfo("Sending filed...");
        },
        complete: function () {
            
        },
        contentType: "application/json; charset=utf-8",
        cache: false,
        dataType: 'json',
        timeout: 1000
    });
}

function onNewSondPlaying(sngTitle, sngUrl, sngPlayListUrl) {
    logInfo("[[[" + sngTitle + "]]] " + sngUrl + " (((" + sngPlayListUrl + ")))");
    
    sendDataToServer(sngTitle, sngUrl, sngPlayListUrl);
}

function logInfo(message){
    console.info("sc-cjs: " + message);
}

var lastSongUrl = null;
var lastSongTitle = null;
var lastSongPlaylistUrl = null;


setInterval(function(){
  //debugger;

  var songDiv = $('div.playbackSoundBadge__title');
  var songLink = songDiv.find('a');
    
   var songTitle = songLink.attr('title');
   var songUri =  songLink.attr('href');
    
    var songURL = 'https://soundcloud.com' + songUri;
    
    if(lastSongUrl != songURL){
        //logInfo(songTitle + " -- " + songURL);
        lastSongUrl = songURL;
	lastSongTitle = songTitle;
    }
}, 50);



var open = window.XMLHttpRequest.prototype.open,
    send = window.XMLHttpRequest.prototype.send,
    onReadyStateChange;

function openReplacement(method, url, async, user, password) {
    var syncMode = async !== false ? 'async' : 'sync';
    if(url.indexOf("m3u") > -1) {
	    lastSongPlaylistUrl = url; 
        onNewSondPlaying(lastSongTitle, lastSongUrl, lastSongPlaylistUrl);
    }
    return open.apply(this, arguments);
}

function sendReplacement(data) {
    // console.warn('Sending HTTP request data : ', data);

    if(this.onreadystatechange) {
        this._onreadystatechange = this.onreadystatechange;
    }
    this.onreadystatechange = onReadyStateChangeReplacement;

    return send.apply(this, arguments);
}

function onReadyStateChangeReplacement() {
    // console.warn('HTTP request ready state changed : ' + this.readyState);
    if(this._onreadystatechange) {
        return this._onreadystatechange.apply(this, arguments);
    }
}

window.XMLHttpRequest.prototype.open = openReplacement;
window.XMLHttpRequest.prototype.send = sendReplacement;




```