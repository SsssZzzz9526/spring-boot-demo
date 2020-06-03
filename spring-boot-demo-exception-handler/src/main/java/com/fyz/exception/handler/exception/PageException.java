package com.fyz.exception.handler.exception;

import com.fyz.exception.handler.enums.StatusEnum;
import lombok.Data;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 21:27
 */
@Data
public class PageException extends BaseException {

    public PageException(StatusEnum status) {
        super(status);
    }

    public PageException(StatusEnum status, Object data) {
        super(status, data);
    }

    public PageException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}