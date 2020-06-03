package com.fyz.exception.handler.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.fyz.exception.handler.dto.UserLoginDTO;
import com.fyz.exception.handler.enums.StatusEnum;
import com.fyz.exception.handler.exception.PageException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/28 19:17
 */
@Controller
public class PageController {

    @GetMapping("/page/test")
    public String page(UserLoginDTO userLoginDTO) {
        Dict dict = Dict.create();
        if (StrUtil.isEmpty(userLoginDTO.getUsername())) {
            dict.set("username", "用户名不能为空");
        }
        if (StrUtil.isEmpty(userLoginDTO.getPassword())) {
            dict.set("password", "密码不能为空");
        }
        throw new PageException(StatusEnum.FAILED, dict);
    }
}