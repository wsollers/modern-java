package dev.wsollers.northwinds;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "dev.wsollers.northwinds.domain")
@EnableJpaRepositories(basePackages = "dev.wsollers.northwinds.repository")
public class DomainJpaTestConfig {}
