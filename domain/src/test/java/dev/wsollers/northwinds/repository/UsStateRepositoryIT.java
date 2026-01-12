package dev.wsollers.northwinds.repository;

import dev.wsollers.IntegrationTests;
import dev.wsollers.northwinds.domain.UsState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(IntegrationTests.class)
@ActiveProfiles("test")
class UsStateRepositoryIT extends BaseRepositoryIT {

    @Autowired
    UsStateRepository usStateRepository;

    @Test
    void fetchMaryland_andVerifyUniquenessAndAbbreviation() {
        Optional<UsState> result = usStateRepository.findByStateName("Maryland");
        assertThat(result).isPresent();
        assertThat(result.get().getStateAbbr()).isEqualTo("MD");
    }

    @Test
    void fetchStatesStartingWithA() {
        List<UsState> states = usStateRepository.findByStateNameStartingWith("A");

        assertThat(states).isNotEmpty();
        assertThat(states).allSatisfy(state ->
                assertThat(state.getStateName()).startsWith("A")
        );

        List<String> stateNames = states.stream().map(UsState::getStateName).toList();
        assertThat(stateNames).contains("Alabama", "Alaska", "Arizona", "Arkansas");
        assertThat(states).hasSizeGreaterThanOrEqualTo(4);
    }

    @Test
    void fetchAllStates() {
        List<UsState> states = usStateRepository.findAll();
        assertThat(states).isNotEmpty();
        assertThat(states).hasSize(51);
    }
}
