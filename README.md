# zeus
> 这是一个 Spring Cloud 项目，使用了 GitHub Action + K8s，集成了单点登陆。
> - 基于 Github Action + Kubernetes 实现了 CI/CD
> - 实现了单点登陆、社交登陆(Github、公众号关注登陆)入口
> - 实现了 RBAC 权限控制

## 编译项目
```shell
mvn -Drevision=1.0.0-SNAPSHOT clean package
```

## 如何启动
1. 配置环境变量，参照`env.example`
2. 配置DNS映射，参照`host.example`
3. 启动对应服务

## 项目基础结构
```shell script
.
├── README.md
├── docs # 相关文档
├── env.example # 环境变量案例
├── host.example # DNS映射案例
├── zeus-gateway # 系统网关
├── zeus-generator # 代码生成器
├── zeus-parent # 依赖管理
├── zeus-services # 服务群
│   └── zeus-service-tpl # 单体服务案例
│       ├── zeus-service-tpl-api # 单体服务内部接口案例
│       └── zeus-service-tpl-start # 单体服务业务功能案例
└── pom.xml
```

## 其他文档
- [基础设施](./docs/基础设施.md)
- [服务设施](./docs/服务设施.md)
- [单点登录(local模式)](./docs/单点登录_local模式.md)

## 相关内容
### API 接口文档系列
> API 接口文档使用的是`knife`可以按照`swagger`在服务群里面进行使用，文档的地址为`http://{网关地址:端口}/doc.html`
