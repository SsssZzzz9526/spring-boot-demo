package com.fyz.orm.jdbc.dao.user;

import com.fyz.orm.jdbc.dao.BaseMapper;
import com.fyz.orm.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/03 22:03
 */
@Repository
public class UserMapper extends BaseMapper<User, Long> {

    @Autowired
    public UserMapper(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    /**
     * 保存用户
     *
     * @param user 用户对象
     * @return 操作影响行数
     */
    public Integer insert(User user) {
        return super.insert(user, true);
    }
}