package dev.wsollers.northwinds.repository;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

public class PostgresTestContainer {

    private static final PostgreSQLContainer<?> INSTANCE;

    static {
        INSTANCE = new PostgreSQLContainer<>(
                DockerImageName.parse("northwinds-postgres:latest")
                        .asCompatibleSubstituteFor("postgres")
                )
                // --- Postgres bootstrap ---
                .withEnv("POSTGRES_USER", "postgres")
                .withEnv("POSTGRES_PASSWORD", "postgres_bootstrap_password")
                .withEnv("POSTGRES_DB", "modern_java")

                // --- Admin role (schema owner / migrations) ---
                .withEnv("ADMIN_DB_USER", "modern_java_admin")
                .withEnv("ADMIN_DB_PASSWORD", "admin_strong_password")

                // --- Application write role ---
                .withEnv("APP_DB_USER", "modern_java_write")
                .withEnv("APP_DB_PASSWORD", "write_strong_password")

                // --- Application read-only role ---
                .withEnv("READ_DB_USER", "modern_java_read")
                .withEnv("READ_DB_PASSWORD", "read_strong_password")

                // --- PgAdmin (harmless here; container will ignore unless used) ---
                .withEnv("PGADMIN_DEFAULT_EMAIL", "admin@example.com")
                .withEnv("PGADMIN_DEFAULT_PASSWORD", "pgadmin_strong_password")
                .withEnv("PGADMIN_PORT", "5050")

                .withDatabaseName("modern_java")
                .withUsername("modern_java_admin")
                .withPassword("admin_strong_password")
                .waitingFor(
                        Wait.forLogMessage("(?s).*==> NORTHWINDS init finished\\..*", 1)
                )
                .withReuse(true);
        INSTANCE.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return INSTANCE;
    }
}