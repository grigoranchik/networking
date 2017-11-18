
Install Chrome extension: https://chrome.google.com/webstore/detail/custom-javascript-for-web/poakhlngfciodnhlhhgnaaelnpjljija?hl=en




```

// Here You can type your custom JavaScript...

function logInfo(message){
    console.info("sc-cjs: " + message);
}

var lastSongUrl = null;

setInterval(function(){
  //debugger;

  var songDiv = $('div.playbackSoundBadge__title');
  var songLink = songDiv.find('a');
    
   var songTitle = songLink.attr('title');
   var songUri =  songLink.attr('href');
    
    var songURL = 'https://soundcloud.com' + songUri;
    
    if(lastSongUrl != songURL){
        logInfo(songTitle + " -- " + songURL);
        lastSongUrl = songURL;
    }
}, 100);





var open = window.XMLHttpRequest.prototype.open,
    send = window.XMLHttpRequest.prototype.send,
    onReadyStateChange;

function openReplacement(method, url, async, user, password) {
    var syncMode = async !== false ? 'async' : 'sync';
    if(url.indexOf("m3u") > -1){
       logInfo(url);
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