package com.fyz.cache.redis.sentinel.controller;

import com.fyz.cache.redis.sentinel.entity.User;
import com.fyz.cache.redis.sentinel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: jiafeimao
 * @create: 2020-06-17 10:30
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users")
    public User saveOrUpdate(User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/api/users/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @DeleteMapping("/api/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
