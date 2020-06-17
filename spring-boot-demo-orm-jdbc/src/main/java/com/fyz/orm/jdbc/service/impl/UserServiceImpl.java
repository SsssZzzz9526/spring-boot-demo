package com.fyz.orm.jdbc.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.fyz.orm.jdbc.constant.Const;
import com.fyz.orm.jdbc.dao.user.UserMapper;
import com.fyz.orm.jdbc.entity.User;
import com.fyz.orm.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/03 22:02
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean save(User user) {
        String raw = user.getPassword();
        String salt = IdUtil.simpleUUID();
        String password = SecureUtil.md5(raw + Const.SALT_PREFIX + salt);
        user.setPassword(password);
        user.setSalt(salt);
        return userMapper.insert(user) == 1;
    }

    @Override
    public boolean delete(Long id) {
        return userMapper.delete(id) == 1;
    }

    @Override
    public boolean update(User user, Long id) {
        return userMapper.updateById(user, id, true) == 1;
    }

    @Override
    public User getUser(Long id) {
        return userMapper.findOneById(id);
    }

    @Override
    public List<User> getUser(User user) {
        return userMapper.findByExample(user);
    }
}