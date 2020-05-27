package com.fyz.log4j2.service;

import com.fyz.log4j2.dao.LogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/27 19:55
 */
@Slf4j
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    public String log(String str) {
        String format = String.format("LogService.log(%s)\n%s", str, logMapper.log(str));
        log.info(format);
        log.error(format);
        return format;
    }
}