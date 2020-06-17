package com.fyz.exception.handler.advice;

import com.fyz.exception.handler.exception.ApiException;
import com.fyz.exception.handler.exception.PageException;
import com.fyz.exception.handler.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 21:17
 */
@Slf4j
@ControllerAdvice
public class PageExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(PageException.class)
    public ModelAndView pageExceptionHandler(PageException e) {
        log.error("【PageException】:{}", e.getMessage());
        ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR_VIEW);
        modelAndView.addObject("data", e.getData());
        return modelAndView;
    }
}