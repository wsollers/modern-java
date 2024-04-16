package dev.wsollers.repository;

import dev.wsollers.domain.Citizen;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CitizenJpaRepository implements CitizenRepository {

  private void createEntityManager() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("citizen");
    EntityManager em = emf.createEntityManager();
  }

  public List<Citizen> getCitizens() {
    return new ArrayList<Citizen>();
  }


}
