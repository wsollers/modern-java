package dev.wsollers.application;

import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LogProbe {
    @Bean
    ApplicationRunner probe() {
        return args -> {
            var log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.azure.identity");
            System.out.println("com.azure.identity effective level = " + log.getEffectiveLevel());
        };
    }
}
