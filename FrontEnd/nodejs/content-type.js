var http = require("http");
 
var server = http.createServer();

server.on("request",function(req,res){
    res.end("hello, world");

});


server.listen(3000,function(){
    console.log("server is running.........")
})