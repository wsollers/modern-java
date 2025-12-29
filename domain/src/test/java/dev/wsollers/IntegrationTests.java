package dev.wsollers;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackages = "dev.wsollers.northwinds.domain")
@EnableJpaRepositories(basePackages = "dev.wsollers.northwinds.repository")
public class IntegrationTests {}
