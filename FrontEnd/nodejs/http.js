var http = require("http");

var server = http.createServer();

server.on('request',function(request,response){

    // console.log(request);
    console.log("收到请求了"+request.url);
    response.write("hello");
    response.end();


});

server.listen(8080, function(){
    //启动成功后的信息
    console.log("test");
});