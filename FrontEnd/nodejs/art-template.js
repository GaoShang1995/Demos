var template = require("./files/node_modules/art-template");
var fs = require("fs");
var http = require("http");

var server = http.createServer();

// console.log(template);
var dir = "D:/download";

server.on("request",function(req,resp){

    if(req.url ==="/template"){
        fs.readFile("./files/template.html",function(err,data){

            if(err){
                return console.log("读取文件失败");
            }else{
            
        
                fs.readdir(dir,function(err,files){
                    if(err){
                        console.log("读取文件路径失败");
                    }else{
        
                        // var content = "";
                        
                        // files.forEach((item) => {
                        //     content += `
                        //     <img src="./icons/folder.gif" alt="[DIR]"> <a href="D:/download/${item}">${item}</a>
                        //     `
                        // })
                        var str = data.toString()
                        var ret = template.render(str,{
                            files:files
                        })

                        // console.log(ret);
                        resp.setHeader("Content-Type","text/html;charset=utf8")
                        resp.end(ret);
                    }
                })
        
            }
        
        
        }) 
    }
    
})

 


server.listen(4500,function(){
    console.log("server is running");
})
// console.log(ret);


