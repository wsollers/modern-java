package dev.wsollers.northwinds.repository;

import dev.wsollers.northwinds.domain.UsState;
import dev.wsollers.IntegrationTests;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(IntegrationTests.class)
@ActiveProfiles("test")
class UsStateRepositoryIT extends BaseRepositoryIT {

    private static final Logger log = LoggerFactory.getLogger(UsStateRepositoryIT.class);

    @Autowired
    EntityManager em;

    @Autowired
    UsStateRepository usStateRepository;

    @Test
    void fetchMaryland_andVerifyUniquenessAndAbbreviation() {
        // when
        Optional<UsState> result = usStateRepository.findByStateName("Maryland");

        assertThat(result.isPresent()).isTrue();

        UsState maryland = result.get();
        assertThat(maryland.getStateAbbr())
                .isEqualTo("MD");
    }

    @Test
    void fetchStatesStartingWithA() {
        List<UsState> states = usStateRepository.findByStateNameStartingWith("A");

        assertThat(states).isNotEmpty();
        assertThat(states).allSatisfy(state ->
                assertThat(state.getStateName()).startsWith("A")
        );

        // Should include Alabama, Alaska, Arizona, Arkansas
        assertThat(states).hasSizeGreaterThanOrEqualTo(4);

        List<String> stateNames = states.stream()
                .map(UsState::getStateName)
                .toList();
        assertThat(stateNames).contains("Alabama", "Alaska", "Arizona", "Arkansas");
        assertThat(stateNames.size()).isEqualTo(4);
    }
}
