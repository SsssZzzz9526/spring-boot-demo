package com.fyz.exception.handler.exception;

import com.fyz.exception.handler.enums.StatusEnum;
import lombok.Data;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 21:23
 */
@Data
public class BaseException extends RuntimeException {

    private int code;

    private String message;

    private Object data;

    public BaseException(StatusEnum status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = null;
    }

    public BaseException(StatusEnum status, Object data) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public BaseException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }
}