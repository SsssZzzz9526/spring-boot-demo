package com.fyz.exception.handler.advice;

import com.fyz.exception.handler.exception.ApiException;
import com.fyz.exception.handler.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 21:17
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public Response<?> apiExceptionHandler(ApiException e) {
        log.error("【ApiException】:{}", e.getMessage());
        return new Response<>(e);
    }
}