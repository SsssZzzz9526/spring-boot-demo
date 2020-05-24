# 项目监控
## spring-boot-starter-actuator
> SpringBoot的Actuator模块实现了应用的监控与管理
## spring-boot-starter-security
> 因SpringBoot Actuator会暴露服务的详细信息，为了保障安全性，建议添加安全控制的相关依赖spring-boot-starter-security，这样在访问应用监控端点时，都需要输入验证信息。
## application.yml注意点
1. spring.security.user.name 是忽略大小写的
2. 通过配置management.server.port和contextPath区分项目上下文路径和端口