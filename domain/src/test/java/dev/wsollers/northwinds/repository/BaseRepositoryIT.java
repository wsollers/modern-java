package dev.wsollers.northwinds.repository;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class BaseRepositoryIT {

    @Container
    static PostgreSQLContainer<?> postgres = PostgresTestContainer.getInstance();

    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        // Spring Boot datasource
        PostgreSQLContainer<?> postgres = PostgresTestContainer.getInstance();

        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("spring.sql.init.mode", () -> "always");
        registry.add("spring.jpa.defer-datasource-initialization", () -> "true");

        // If you want to be explicit:
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");



        // Optional: make sure Flyway is on
        //registry.add("spring.flyway.enabled", () -> "true");
    }

}
