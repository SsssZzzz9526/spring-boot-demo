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
public class ApiException extends BaseException {

    public ApiException(StatusEnum status) {
        super(status);
    }

    public ApiException(StatusEnum status, Object data) {
        super(status, data);
    }

    public ApiException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}