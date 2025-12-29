package dev.wsollers.northwinds.repository;


import dev.wsollers.northwinds.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
