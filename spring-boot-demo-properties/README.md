# spring-boot 处理配置
## 自定义配置
1. @Value(${key:defaultValue})
2. @ConfigurationProperties(prefix = )
3. pom文件
   > 在 resources/META-INF/additional-spring-configuration-metadata.json 中配置
   > 可以去除 application.yml 中自定义配置的红线警告，并且为自定义配置添加 hint 提醒

## 环境配置
application.yml
spring.profiles.active 指定使用哪个配置环境