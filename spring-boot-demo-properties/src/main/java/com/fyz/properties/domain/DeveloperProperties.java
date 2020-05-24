package com.fyz.properties.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/24 18:18
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperties {
    private String name;

    private String website;

    private String qq;
}