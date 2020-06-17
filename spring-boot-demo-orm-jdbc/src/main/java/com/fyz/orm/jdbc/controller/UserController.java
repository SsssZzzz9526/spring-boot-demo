package com.fyz.orm.jdbc.controller;

import cn.hutool.core.lang.Dict;
import com.fyz.orm.jdbc.entity.User;
import com.fyz.orm.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/03 23:41
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user")
    public Dict save(@RequestBody User user) {
        boolean save = userService.save(user);
        return Dict.create().set("code", save ? 200 : 500).set("msg", save ? "成功" : "失败").set("data", save ? user : null);
    }

    @DeleteMapping("/user/{id}")
    public Dict delete(@PathVariable Long id) {
        boolean delete = userService.delete(id);
        return Dict.create().set("code", delete ? 200 : 500).set("msg", delete ? "成功" : "失败");
    }

    @PutMapping("/user/{id}")
    public Dict update(@RequestBody User user, @PathVariable Long id) {
        boolean update = userService.update(user, id);
        return Dict.create().set("code", update ? 200 : 500).set("msg", update ? "成功" : "失败").set("data", update ? user : null);
    }

    @GetMapping("/user/{id}")
    public Dict getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return Dict.create().set("code", 200).set("msg", "成功").set("data", user);
    }

    @GetMapping("/user")
    public Dict getUser(User user) {
        List<User> userList = userService.getUser(user);
        return Dict.create().set("code", 200).set("msg", "成功").set("data", userList);
    }
}