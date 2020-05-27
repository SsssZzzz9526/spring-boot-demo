package com.fyz.log4j2.controller;

import com.fyz.log4j2.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/27 19:55
 */
@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/api/logback/{str}")
    public String log(@PathVariable String str) {
        String format = String.format("LogController.log(%s)\n%s", str, logService.log(str));
        log.info(format);
        log.error(format);
        return format;
    }
}