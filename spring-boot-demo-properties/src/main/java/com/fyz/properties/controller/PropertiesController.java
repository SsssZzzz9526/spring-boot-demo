package com.fyz.properties.controller;

import cn.hutool.core.lang.Dict;
import com.fyz.properties.domain.ApplicationProperties;
import com.fyz.properties.domain.DeveloperProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-boot-demo
 * @description:
 * @author: SsssZzzz9526
 * @created: 2020/05/24 18:25
 */
@RestController
public class PropertiesController {
    private final ApplicationProperties applicationProperties;
    private final DeveloperProperties developerProperties;

    @Autowired
    public PropertiesController(ApplicationProperties applicationProperties, DeveloperProperties developerProperties) {
        this.applicationProperties = applicationProperties;
        this.developerProperties = developerProperties;
    }

    @GetMapping("/property")
    public Dict index() {
        return Dict.create()
                .set("application", this.applicationProperties)
                .set("developer", this.developerProperties);
    }
}