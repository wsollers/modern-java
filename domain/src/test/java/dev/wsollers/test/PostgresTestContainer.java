package dev.wsollers.test;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class PostgresTestContainer {

    @Container
    protected static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:18-alpine")
                    .withDatabaseName("northwinds")
                    .withUsername("northwinds")
                    .withPassword("northwinds");
}
