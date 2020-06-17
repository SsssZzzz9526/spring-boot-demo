package com.fyz.exception.handler.model;

import com.fyz.exception.handler.enums.StatusEnum;
import com.fyz.exception.handler.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.fyz.exception.handler.enums.StatusEnum.*;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 19:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int code;

    private String message;

    private boolean success;

    private T data;

    public static <T> Response<T> success() {
        return Response.success(null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(SUCCESS.getCode(), SUCCESS.getMessage(), true, data);
    }

    public static <T> Response<T> error() {
        return Response.error(null);
    }

    public static <T> Response<T> error(T data) {
        return new Response<>(FAILED.getCode(), FAILED.getMessage(), false, data);
    }

    public Response(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
        this.success = Objects.equals(statusEnum, SUCCESS);
    }

    public Response(BaseException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.success = false;
        this.data = (T) e.getData();
    }
}