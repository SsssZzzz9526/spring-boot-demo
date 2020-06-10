package com.fyz.orm.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/10 22:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orm_department")
@ToString(callSuper = true)
public class Department extends BaseEntity{

    private static final long serialVersionUID = 9050990220753482736L;

    /**
     * 部门名
     */
    @Column(name = "name", columnDefinition = "varchar(255) not null")
    private String name;

    /**
     *上级部门id
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "superior", referencedColumnName = "id")
    private Department superior;

    /**
     * 所属层级
     */
    @Column(name = "levels", columnDefinition = "int not null default 0")
    private Integer levels;

    /**
     * 排序
     */
    @Column(name = "order_no", columnDefinition = "int not null default 0")
    private Integer orderNo;

    /**
     * 子部门
     */
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "superior")
    private List<Department> children;

    /**
     * 部门职员
     */
    @ManyToMany(mappedBy = "departments")
    private List<User> employee;
}