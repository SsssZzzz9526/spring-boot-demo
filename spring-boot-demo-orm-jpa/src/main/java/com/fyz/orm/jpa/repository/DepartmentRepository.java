package com.fyz.orm.jpa.repository;

import com.fyz.orm.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/06/10 22:35
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * @Description: 根据层级查询
     * @param level
     * @return: java.util.List<com.fyz.orm.jpa.entity.Department>
     * @Creator: fyz
     * @Date: 2020/6/10 22:36
     */
    List<Department> findDepartmentsByLevels(Integer level);
}