package com.fyz.orm.jpa.repository;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.fyz.orm.jpa.SpringBootDemoOrmJpaApplicationTest;
import com.fyz.orm.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Slf4j
public class UserRepositoryTest extends SpringBootDemoOrmJpaApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User.builder().name("testSave3").password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave3@xkcoding.com").phoneNumber("17300000003").status(1).lastLoginTime(new DateTime()).build();
        userRepository.save(testSave3);

        assertNotNull(testSave3.getId());
        Optional<User> byId = userRepository.findById(testSave3.getId());
        assertTrue(byId.isPresent());
        log.debug("【byId】= {}", byId.get());
    }

    @Test
    public void testDelete() {
        long before = userRepository.count();
        userRepository.deleteById(1L);
        long after = userRepository.count();
        assertEquals(before - 1, after);
    }

    @Test
    public void testUpdate() {
        final String newName = "test update name";
        userRepository.findById(1L).ifPresent(user -> {
            user.setName(newName);
            userRepository.save(user);
        });
        Optional<User> byId = userRepository.findById(1L);
        assertEquals(newName, byId.get().getName());
    }

    @Test
    public void testFindOne() {
        Optional<User> byId = userRepository.findById(1L);
        assertTrue(byId.isPresent());
        log.debug("【byId】= {}", byId.get());
    }

    @Test
    public void testFindAll() {
        List<User> all = userRepository.findAll();
        assertNotEquals(0, all.size());
        log.debug("【users】= {}", all);
    }

    @Test
    public void testFindPage() {
        initData();

        final int pageNum = 1;
        final int pageSize = 5;

        Sort descById = Sort.by(Sort.Direction.DESC, "id");

        PageRequest page = PageRequest.of(pageNum, pageSize, descById);

        Page<User> pageInfo = userRepository.findAll(page);

        assertEquals(5, pageInfo.getSize());
        assertEquals(userRepository.count(), pageInfo.getTotalElements());

        log.debug("【id】= {}", pageInfo.getContent().stream().map(User::getId).collect(Collectors.toList()));
    }

    /**
     * 初始化10条数据
     */
    private void initData() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            String salt = IdUtil.fastSimpleUUID();
            int index = 3 + i;
            User user = User.builder().name("testSave" + index).password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave" + index + "@xkcoding.com").phoneNumber("1730000000" + index).status(1).lastLoginTime(new DateTime()).build();
            userList.add(user);
        }
        userRepository.saveAll(userList);
    }
}