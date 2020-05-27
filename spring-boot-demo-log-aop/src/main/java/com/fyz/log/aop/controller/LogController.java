package com.fyz.log.aop.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 00:27
 */
@RestController
public class LogController {

    @GetMapping("/api/log/aop")
    public Dict sayHi(String who) {
        return Dict.create().set("who", StrUtil.isBlank(who) ? "me" : who);
    }
}