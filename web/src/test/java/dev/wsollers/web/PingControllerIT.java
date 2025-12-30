package dev.wsollers.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

import dev.wsollers.test.PostgresTestContainer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

@ActiveProfiles("test")
@EntityScan(basePackages = "dev.wsollers.northwinds.domain")
@EnableJpaRepositories(basePackages = "dev.wsollers.northwinds.repository")
class PingControllerIT  extends PostgresTestContainer {

    @SpringBootApplication(scanBasePackages = "dev.wsollers")
    static class TestApp { }

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void ping_returnsOkJson() {
        var body = rest.getForObject("http://localhost:" + port + "/api/ping", String.class);
        assertThat(body).contains("\"status\":\"ok\"");
    }
}
