package com.fyz.orm.jpa.repository;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.fyz.orm.jpa.SpringBootDemoOrmJpaApplicationTest;
import com.fyz.orm.jpa.entity.Department;
import com.fyz.orm.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class DepartmentRepositoryTest extends SpringBootDemoOrmJpaApplicationTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() {
        Collection<Department> departmentList = departmentRepository.findDepartmentsByLevels(0);

        if (departmentList.size() == 0) {
            Department testSave1 = Department.builder().name("testSave1").orderNo(0).levels(0).superior(null).build();
            Department testSave1_1 = Department.builder().name("testSave1_1").orderNo(0).levels(1).superior(testSave1).build();
            Department testSave1_2 = Department.builder().name("testSave1_2").orderNo(0).levels(1).superior(testSave1).build();
            Department testSave1_1_1 = Department.builder().name("testSave1_1_1").orderNo(0).levels(2).superior(testSave1_1).build();
            departmentList.add(testSave1);
            departmentList.add(testSave1_1);
            departmentList.add(testSave1_2);
            departmentList.add(testSave1_1_1);
            departmentRepository.saveAll(departmentList);

            Collection<Department> deptall = departmentRepository.findAll();
            log.debug("【部门】= {}", JSONArray.toJSONString((List) deptall));
        }

        userRepository.findById(1L).ifPresent(user -> {
            user.setName("添加部门");
            Department dept = departmentRepository.findById(2L).get();
            user.setDepartments(CollUtil.newArrayList(dept));
            userRepository.save(user);
        });
        log.debug("用户部门={}", JSONUtil.toJsonStr(userRepository.findById(1L).get().getDepartments()));

        departmentRepository.findById(2L).ifPresent(dept -> {
            List<User> userlist = dept.getEmployee();
            //关联关系由user维护中间表，department userlist不会发生变化，可以增加查询方法来处理  重写getUserList方法
            log.debug("部门下用户={}", JSONUtil.toJsonStr(userlist));
        });


    }
}