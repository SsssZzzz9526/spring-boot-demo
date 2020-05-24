package com.fyz.properties.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/24 18:17
 */
@Data
@Component
public class ApplicationProperties {
    @Value("${application.name}")
    private String name;

    @Value("${application.version}")
    private String version;
}