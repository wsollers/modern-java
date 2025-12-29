package dev.wsollers.northwinds.repository.impl;

import dev.wsollers.northwinds.domain.UsState;
import dev.wsollers.northwinds.repository.UsStateRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class UsStateRepositoryImpl {

    //@PersistenceContext
    private EntityManager entityManager;


    public Optional<UsState> findByStateNameXXX(String stateName) {
        return entityManager.createQuery(
                        "SELECT s FROM UsState s WHERE s.stateName = :stateName", UsState.class)
                .setParameter("stateName", stateName)
                .getResultStream()
                .findFirst();
    }
}
