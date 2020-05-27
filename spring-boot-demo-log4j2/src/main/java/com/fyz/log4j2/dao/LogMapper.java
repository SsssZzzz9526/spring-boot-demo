package com.fyz.log4j2.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/27 19:56
 */
@Slf4j
@Repository
public class LogMapper {

    public String log(String str) {
        String format = String.format("LogMapper.log(%s)\n", str);
        log.info(format);
        log.error(format);
        return format;
    }
}