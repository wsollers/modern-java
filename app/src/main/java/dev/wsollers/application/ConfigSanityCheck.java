package dev.wsollers.application;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class ConfigSanityCheck {
    ConfigSanityCheck(Environment env) {
        System.out.println("Has spring.datasource.url? " + (env.getProperty("spring.datasource.url") != null));
        System.out.println("Has spring.datasource.url? " + env.getProperty("spring.datasource.url"));
        System.out.println("Has spring.datasource.username? " + (env.getProperty("spring.datasource.username") != null));
        System.out.println("Has spring.datasource.username? " + env.getProperty("spring.datasource.username"));
        System.out.println("Has spring.datasource.password? " + (env.getProperty("spring.datasource.password") != null));
    }
}
