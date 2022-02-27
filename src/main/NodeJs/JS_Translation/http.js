'use strict';

// 导包
var http = require('http');
// 创建一个httpserver
http.createServer(function (request, response) {
    // 浏览器以文本的方式解析
    response.writeHead(200, { 'Content-type': 'text/html' });
    // 浏览器输出内容
    response.end("<strong>hello</strong>");
}).listen(8888);
console.log('hello');