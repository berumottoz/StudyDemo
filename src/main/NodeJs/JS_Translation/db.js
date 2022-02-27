"use strict";

// 导包
var mysql = require('mysql');
// 创建链接对象
var connection = mysql.createConnection({
    host: "127.0.0.1",
    port: 3306,
    user: "root",
    password: "zxp19950707",
    database: "test"
});
// 打开链接
connection.connect();
// 执行CRUD
connection.query("select * from test", function (error, results, fields) {
    if (error) {
        throw error;
    }
    console.log("results:", results);
});
connection.end();