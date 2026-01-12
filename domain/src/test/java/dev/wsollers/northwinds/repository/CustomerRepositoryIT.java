package dev.wsollers.northwinds.repository;

import dev.wsollers.IntegrationTests;
import dev.wsollers.northwinds.domain.UsState;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(IntegrationTests.class)
@ActiveProfiles("test")
class CustomerRepositoryIT extends BaseRepositoryIT {

    @Autowired
    EntityManager em;

    @BeforeAll
    static void show() { }

    @AfterAll
    static void dumpLogs() {
        System.out.println("==== Container logs ====");
        System.out.println(getInstance().getLogs());
    }

    @Test
    void smoke() {
        Long one = ((Number) em.createNativeQuery("select 1").getSingleResult()).longValue();
        assertEquals(1L, one);
    }

    @Test
    void marylandIsAState() {
        em.createQuery("SELECT s FROM UsState s WHERE s.stateName = :stateName", UsState.class)
                .setParameter("stateName", "Maryland")
                .getResultStream()
                .findFirst();
    }
}
