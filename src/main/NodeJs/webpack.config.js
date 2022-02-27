// 导入path模块
const path = require("path")
// 打包规则
module.exports = {
    // 唯一入口文件
    entry: "./webpack/index.js",
    // 输出配置
    output: {
        // 打包后的文件存放的路径(绝对路径)
        path:"E:/IDEA_worksapce/StudyDemo/src/main/NodeJs/webpack/webpack/dist",
        // 打包后输出文件的文件名
        filename: "bundle.js"

    },
    module: {
        // 规则
        rules: [{
            // 把项目中的所有.css结尾的文件进行打包
            test: /\.css$/,
            use: ["style-loader","css-loader"]
        }]
    }
}