## 一、线上模式
1. 配置 hostname
```shell script
127.0.0.1 sso.hocgin.local
127.0.0.1 api.hocgin.local
127.0.0.1 logistics.hocgin.local
```
2. 启动服务

- gateway
- sso-server

3. 访问链接
> http://example.hocgin.local:20001/doc.html

---
## 二、开发模式
> 免登陆
1. 启动对应服务
2. 请求头添加登陆的`X-Username`
3. 访问对应接口
