package com.fyz.helloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDemoHelloWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoHelloWorldApplication.class, args);
    }


    /**
     * @Description: Hello world
     * @param who
     * @return: java.lang.String
     *
     * @Creator: fyz
     * @Date: 2020/5/24 13:22
     *
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
    }
}
