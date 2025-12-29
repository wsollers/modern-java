package dev.wsollers.northwinds.repository;

import dev.wsollers.northwinds.domain.UsState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsStateRepository extends JpaRepository<UsState, Long> {
    Optional<UsState> findByStateName(String stateName);

    List<UsState> findByStateNameStartingWith(String firstCharacter);
}