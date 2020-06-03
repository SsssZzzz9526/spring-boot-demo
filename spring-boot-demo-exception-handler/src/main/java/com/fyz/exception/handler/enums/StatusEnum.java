package com.fyz.exception.handler.enums;

import lombok.Getter;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 19:14
 */
@Getter
public enum StatusEnum {
    SUCCESS(200, "请求成功"),
    FAILED(200, "请求失败"),

    ;
    private int code;

    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}