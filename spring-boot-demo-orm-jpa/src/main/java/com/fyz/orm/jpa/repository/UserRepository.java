package com.fyz.orm.jpa.repository;

import com.fyz.orm.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/10 22:35
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}