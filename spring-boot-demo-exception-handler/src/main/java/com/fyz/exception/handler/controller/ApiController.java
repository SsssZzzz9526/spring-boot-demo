package com.fyz.exception.handler.controller;

import com.fyz.exception.handler.enums.StatusEnum;
import com.fyz.exception.handler.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 19:16
 */
@RestController
public class ApiController {

    @GetMapping("/api/test")
    public void api() {
        throw new ApiException(StatusEnum.FAILED);
    }
}