var onmessage = function(event){
    var obj = event.data;
   
    var result = 0;
    for(var i = parseInt(obj.start);i<=parseInt(obj.end);i++){
        result +=i;
    }

    postMessage(result);
}