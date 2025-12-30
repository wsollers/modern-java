package dev.wsollers.northwinds.repository;

import dev.wsollers.northwinds.domain.UsState;
import dev.wsollers.IntegrationTests;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
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
import dev.wsollers.IntegrationTests;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(IntegrationTests.class)
@ActiveProfiles("test")
class CustomerRepositoryIT extends BaseRepositoryIT {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryIT.class);

    @Autowired
    EntityManager em;


    @BeforeAll
    static void show() {
    }

    @AfterAll
    static void dumpLogs() {
        System.out.println("==== Container logs ====");
        System.out.println(getInstance().getLogs());
    }

    @Test
    void smoke() {
        Long one = ((Number) em
                .createNativeQuery("select 1")
                .getSingleResult())
                .longValue();

        assertEquals(1L, one);
    }
    @Test
    void MarylandIsAState() {
        em.createQuery(
                        "SELECT s FROM UsState s WHERE s.stateName = :stateName", UsState.class)
                .setParameter("stateName", "Maryland>")
                .getResultStream()
                .findFirst();
    }
}
