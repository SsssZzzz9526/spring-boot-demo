package com.fyz.orm.jdbc.entity;

import com.fyz.orm.jdbc.annotatino.Column;
import com.fyz.orm.jdbc.annotatino.PK;
import com.fyz.orm.jdbc.annotatino.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/03 21:59
 */
@Data
@Table(name = "orm_user")
public class User implements Serializable {
    /**
     * 主键
     */
    @PK
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 加密使用的盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 状态，-1：逻辑删除，0：禁用，1：启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
}