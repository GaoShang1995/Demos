var http = require('http');
var fs = require("fs");


var server = http.createServer();

server.on("request",function(req,res){

    var url = req.url;
    console.log(url);
    // console.log(url);
    // if(url === "html"){
        // res.end("<p>你好</p>");

        fs.readFile('./files/readme.txt',function(err,data){
            if(err){
                res.setHeader("Content-Type","text/plian;charset=utf-8");
                res.end("文件读取失败，请稍后重试");
            }else{
                res.setHeader("Content-Type","text/html;charset=utf-8");
                res.end(data);
            }
        })
    // }

})

server.listen(3000,function(){
    console.log("server is running......");
})