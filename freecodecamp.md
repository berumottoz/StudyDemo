# freecodecamp学习笔记

***
## 后端开发和 APIs
### 使用 NPM 管理软件包
1. package.json 文件是所有 Node.js 项目和 npm 包的枢纽
2. 最常见的信息
   1. "author": "Jane Doe", 项目的创建者
   2. "description": "A project that does something awesome", 它也是一种很好的总结项目的方式， 可以帮助其它开发者、维护者甚至自己在未来快速地了解项目，对于任何一个 Node.js 项目来说都非常重要。
   3. "keywords": [ "descriptive", "related", "words" ], 字段中可以使用相关的关键字描述项目
   4. "license": "MIT", 开源项目常见的协议有 MIT 和 BSD 等(附件_开源协议)
   5. "version": "1.2.0", 版本号
   6. "dependencies": {"package-name": "version","express": "4.14.0"}, 依赖管理
   7. "package": "MAJOR.MINOR.PATCH",它是一种旨在使管理依赖项更加容易的软件版本控制的行业标准。 在 npm 上发布的库、框架或其它工具都应该使用语义化版本，以便让用户清晰地知道如果项目升级将带来哪些改变。
      1. 当做了不兼容的 API 修改，应该增加主版本号（MAJOR）
      2. 当新增了向下兼容的新功能时，应该增加次版本号（MINOR）
      3. 当修复了向下兼容的 bug 时，应该增加修订号（PATCH）
      4. "package": "~1.3.8", 依赖项的版本号前加一个波浪号（~），以让 npm 依赖项更新到最新的修订版
      5. "package": "^1.3.8", 如果使用插入符号（^）作为版本前缀，npm 将被允许更新到任何 1.x.x 版本

### Express( let app = express() )
#### 启动一个 Express 服务
当 GET 请求 /（根路由 ）时，使用 app.get() 方法响应一个“Hello Express”字符串。 通过查看日志确保代码正常运行，如果使用 Replit 可以在预览中查看结果。
   ```
   app.get("/", function(req, res) {
      res.send("Hello Express");
      });
   ```
#### 提供 HTML 文件服务 
通过 res.sendFile(path) 方法给请求响应一个文件， 可以把它放到路由处理 app.get('/', ...) 中
   ```
   app.get("/", function(req, res) {
      res.sendFile(__dirname + "/views/index.html");
   });
   ```

#### 提供静态资源服务
1. HTML 服务器通常有一个或多个用户可以访问的目录。 你可以将应用程序所需的静态资源 (样式表、脚本、图片) 放在那里。
2. 在 Express 中可以使用中间件 express.static(path) 来设置此功能，它的参数 path 就是包含静态资源文件的绝对路径。
3. 使用 app.use(path, middlewareFunction) 方法来加载一个中间件， 它的第一个参数 path 是可选的， 如果没设置第一个参数，那么所有的请求都会经过这个中间件处理。
```
app.use("/public", express.static(__dirname + "/public"));
```

#### 在指定路由上提供 JSON 服务
1. 创建一个路径为 /json 且返回数据是 JSON 格式的路由， 可以像之前那样用 app.get() 方法来做。 然后在路由处理部分使用 res.json() 方法，并传入一个对象作为参数， 这个方法会结束请求响应循环（request-response loop），然后返回数据。
2. 一个有效的对象通常是这种结构：{key: data}， data 可以是数字、字符串、嵌套对象或数组， data 也可以是变量或者函数返回值，在这种情况下，它们先求值再转成字符串。
```
app.get("/json", (req, res) => {
  res.json({
    message: "Hello json"
  });
});
```

#### 使用 .env 文件
1. .env 文件是一个用于将环境变量传给应用程序的隐藏文件, 这是一个除了开发者之外没人可以访问的私密文件，它可以用来存储你想保密或者隐藏的数据
2. 在应用程序中可以通过 process.env.VAR_NAME 访问到环境变量
3. process.env 对象是 Node 程序中的一个全局对象，可以给这个变量传字符串
4. 习惯上，变量名全部大写，单词之间用下划线分隔。
5. .env 是一个 shell 文件
```
app.get("/json", (req, res) => {
  var test = "Hello json";
  if (process.env['MESSAGE_STYLE'] === "uppercase") {
    test = test.toUpperCase();
  }

  res.json({
    message: test
  });
});
```

#### 实现一个根级的请求记录中间件
1. 中间件函数是一个接收 3 个参数的函数，这 3 个参数分别是：请求对象、响应对象和在应用的请求-响应循环中的下一个函数。
2. 要在根层级安装中间件函数，我们可以使用 app.use(<mware-function>) 方法。 在这种情况下，该函数将对所有请求执行，但也可以设置更具体的条件来执行
3. 如果你希望某个函数只针对 POST 请求执行，可以使用 app.post(<mware-function>) 方法。 所有的 HTTP 动词（GET、DELETE、PUT……）都存在类似的方法。
4. 构建一个简单的日志记录器。 对于每个请求，它应该在控制台中打印一个采用以下格式的字符串：GET /json - ::ffff:127.0.0.1
```
app.use(function middleware(req, res, next) {
  console.log(req.method + " " + req.path + " - ::ffff:" + req.ip);
  next();
});
```

#### 通过链式调用中间件来创建时间服务
1. 使用 app.METHOD(path, middlewareFunction) 可以在指定的路由挂载中间件， 中间件也可以链接在路由定义中。
2. 此方法可用于将服务操作拆分为较小的单元， 这可以让应用拥有更好的结构，也便于在不同的位置上复用代码； 此方法还可用于对数据执行某些验证。 可以在每一个中间件堆栈中，阻止当前链的执行，并将控制权传递给专门设计用于处理错误的函数； 或者可以将控制权传递给下一个匹配的路由，以处理特殊情况
```
app.get("/now", (req, res, next) => {
  req.time = new Date().toString();
  next();
}, (req, res) => {
  res.json({
    time: req.time
  });
});
```

#### 从客户端获取输入的路由参数
1. 路由参数是由斜杠（/）分隔的 URL 命名段， 每一小段能捕获与其位置匹配的 URL 部分的值， 捕获的值能够在 req.params 对象中找到。
```
路由地址：'/user/:userId/book/:bookId'
实际请求 URL：'/user/546/book/6754'
req.params：{userId: '546', bookId: '6754'}
```
2. 在路由 GET /:word/echo 中构建一个响应服务， 响应一个采用 {echo: word} 结构的 JSON 对象。
```
app.get("/:word/echo", (req, res) => {
  const { word } = req.params;
  res.json({
    echo: word
  });
});
```

#### 从客户端获取输入的查询参数
1. 从客户端获取输入的另一种常见方式是使用查询字符串对路由路径中的数据进行编码， 查询字符串使用标记（?）分隔，并且包含键值对 field=value， 每对键值使用连字号（&）分隔。
```
路由地址：'/library'
实际请求 URL：'/library?userId=546&bookId=6754'
req.query：{userId: '546', bookId: '6754'}
```
2. 构建一个 API 接口，使用路由挂载在 GET /name 上， 使用一个 JSON 文件来响应，它的结构是这样的：{ name: 'firstname lastname'}， 名字（first name）和姓氏（last name）参数应该编码在查询参数中，例如：?first=firstname&last=lastname。
```
app.get("/name", function(req, res) {
  var firstName = req.query.first;
  var lastName = req.query.last;
  var { first: firstName, last: lastName } = req.query;
  res.json({
    name: `${firstName} ${lastName}`
  });
});
```
#### 使用 body-parser 来解析 POST 请求
1. 在这POST请求中，数据不会出现在 URL 中，而是隐藏在请求正文中。 请求正文也是 HTML 请求的一部分，被称为负载。
```
"dependencies": {
    "body-parser": "^1.19.0",
    "express": "^4.17.1"
}

let bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({ extended: false }));
```
#### 从 POST 请求中获取数据

### MongoDB 和 Mongoose
#### 安装和设置 Mongoose
1. 参照附件2 创建并链接MongoDB Atlas
2. ```const mongoose = require('mongoose'); mongoose.connect(process.env.MONGO_URI);```
#### 创建一个模型（Model）


## 附件
1. 开源协议![开源协议](https://img-blog.csdnimg.cn/20190411093420291.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zMzI3ODc3Mg==,size_16,color_FFFFFF,t_70)
2. [MongoDB Atlas 入门教程](https://www.freecodecamp.org/chinese/news/get-started-with-mongodb-atlas/)
