package dev.wsollers.northwinds.repository;

import dev.wsollers.northwinds.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ContextConfiguration(classes = CustomerRepositoryIT.JpaTestConfig.class)
class CustomerRepositoryIT {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void canQueryCustomer() {
        // replace with a real ID that exists in Northwinds
        Customer c = customerRepository.findById("ALFKI").orElse(null);
        assertThat(c).isNotNull();
    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
    @EntityScan(basePackageClasses = Customer.class)
    static class JpaTestConfig { }
}
