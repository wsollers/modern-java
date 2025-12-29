package dev.wsollers.northwinds.repository;

import dev.wsollers.test.IntegrationTests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.wait.strategy.Wait;

@Testcontainers
@SpringBootTest(classes = IntegrationTests.class)
@ActiveProfiles("test")
class CustomerRepositoryIT {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryIT.class);

    @Container
    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(
                    DockerImageName.parse("northwinds-postgres:latest")
                            .asCompatibleSubstituteFor("postgres")
            )
                    .withDatabaseName("modern_java")
                    .withUsername("modern_java_write")
                    .withPassword("write_strong_password")
                    .waitingFor(Wait.forListeningPort());;

    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        // Spring Boot datasource
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        // If you want to be explicit:
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");

        // Flyway will automatically use spring.datasource.* by default, but this removes ambiguity:
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);

        // Optional: make sure Flyway is on
        registry.add("spring.flyway.enabled", () -> "true");
    }


    @Test
    void smoke() {
        // your repository query here
        System.out.println("JDBC URL = " + postgres.getJdbcUrl());

    }

    static {
        try {
            postgres.start();
        } catch (Exception e) {
            System.err.println("==== Container logs ====");
            System.err.println(postgres.getLogs());
            throw e;
        }
    }
}
