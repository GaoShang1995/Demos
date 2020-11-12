
//在node中引入fs核心模块，在fs核心模块中提供了所有文件操作相关的api
var fs = require("fs");
fs.readFile("./files/readme.txt",function(error,data){
    console.log(data.toString());

})

fs.writeFile("./files/writeme.txt","你好，我是nodejs",function(error){
    console.log(error);
})
// console.log(file);
