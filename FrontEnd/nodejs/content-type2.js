var http = require("http");
 
var server = http.createServer();

server.on("request",function(req,res){
    // 不设置返回头，则会当作html解析
    // text/plain 普通文本
    // text/html  html文本
    res.setHeader('Content-Type','text/plain; charset=utf-8');
    res.end("hello, 世界");

});


server.listen(5000,function(){
    console.log("server is running.........")
})