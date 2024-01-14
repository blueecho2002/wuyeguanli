# 小区物业管理系统
## 前端

前端项目占用本地 `8080` 端口

安装项目依赖

```
npm install
```

面向开发的编译和热重载

```
npm run serve
```

编译后访问 http://localhost:8080/ 进入小区物业管理系统

## 后端

后端项目占用本地 `8081` 端口

在IDEA中运行项目启动后端

由于后端项目部署在本地，在实现住户付费功能时，由于支付宝服务器在支付成功后无法访问本地机器内网的回调URL，因此需要使用内网穿透工具（https://natapp.cn/） 实现外网访问，需要将 `application.properties` 文件中的 `alipay.notifyUrl` 内容修改为内网穿透工具生成的外网访问连接

## 数据库

数据库部署在IP地址为 `47.120.47.223` 的云服务器，端口为 `3306`

通过后端项目中 `application.properties` 文件中的 `spring.datasource.url` 、`spring.datasource.username` 、`spring.datasource.password` 修改数据库配置

