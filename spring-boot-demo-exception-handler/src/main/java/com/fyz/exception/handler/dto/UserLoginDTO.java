package com.fyz.exception.handler.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 21:41
 */
@Data
@Accessors(chain = true)
public class UserLoginDTO {

    private String username;

    private String password;
}