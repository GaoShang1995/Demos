var http = require("http");
var server  = http.createServer();
server.on("request",function(req ,resp){
    console.log("收到请求了，路径是"+ req.url);
})


server.listen(8080,function(){
    console.log("服务启动成功");
})