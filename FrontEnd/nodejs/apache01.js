var http = require("http");
var fs = require("fs");

var server = http.createServer();

var dir = "D:/download"
server.on("request",function(req,res){
    var url = req.url;
    fs.readFile("./files/template.html",function(err,data){
        if(err){
            return res.end("404 Not Found");
        }

        //1.获取目录列表
        fs.readdir(dir,function(err,files){
            if(err){
                return res.end("cannot find files");
            }
            // console.log(files);
            var content = "";
            
            files.forEach((item) => {
                content += `
                <img src="./icons/folder.gif" alt="[DIR]"> <a href="D:/download/${item}">${item}</a>
                `
            })

            //2.替换页面内容
            data = data.toString();
            data = data.replace("......",content);
            
            res.setHeader("Content-Type","text/html;charset=utf8")
            res.end(data);
        });

        
    })

})

server.listen(4000,function(){
    console.log("server is running");
})



