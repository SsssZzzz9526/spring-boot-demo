package com.fyz.log4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/27 19:55
 */
@SpringBootApplication
public class SpringBootDemoLog4j2Application {

    public static void main(String[] args) {
//        log4j2 全局异步处理
        System.setProperty("Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(SpringBootDemoLog4j2Application.class, args);
    }
}