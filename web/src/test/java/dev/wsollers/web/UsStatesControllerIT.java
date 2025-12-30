package dev.wsollers.web;

import dev.wsollers.northwinds.domain.UsState;

import dev.wsollers.test.PostgresTestContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@EntityScan(basePackages = "dev.wsollers.northwinds.domain")
@EnableJpaRepositories(basePackages = "dev.wsollers.northwinds.repository")
class UsStatesControllerIT extends PostgresTestContainer {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void listReturnsJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<List<UsState>> response = restTemplate.exchange(
                "/api/us-states/list",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody().size()).isGreaterThanOrEqualTo(50); // All US states
    }

    @Test
    void listReturnsXml() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_XML));

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/us-states/list",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getContentType().toString()).contains("application/xml");
        assertThat(response.getBody()).contains("<item>", "<stateName>", "<stateAbbr>");

    }

    @Test
    void listContainsMaryland() {
        ResponseEntity<List<UsState>> response = restTemplate.exchange(
                "/api/us-states/list",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .extracting(UsState::getStateName)
                .contains("Maryland");
    }
}