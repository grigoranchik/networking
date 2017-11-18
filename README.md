// Here You can type your custom JavaScript...

var lastSongUrl = null;

setInterval(function(){
  //debugger;

  var songDiv = $('div.playbackSoundBadge__title');
  var songLink = songDiv.find('a');
    
   var songTitle = songLink.attr('title');
   var songUri =  songLink.attr('href');
    
    var songURL = 'https://soundcloud.com' + songUri;
    
    if(lastSongUrl != songURL){
        console.info("ignition: " + songTitle + " -- " + songURL);
        lastSongUrl = songURL;
    }
}, 100);





var open = window.XMLHttpRequest.prototype.open,
    send = window.XMLHttpRequest.prototype.send,
    onReadyStateChange;

function openReplacement(method, url, async, user, password) {
    var syncMode = async !== false ? 'async' : 'sync';
    if(url.indexOf("m3u") > -1){
        console.info(
            "ignition: "   +
            //syncMode +
            //' HTTP request : ' +
            //method +
            ' ' +
            url
        );
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