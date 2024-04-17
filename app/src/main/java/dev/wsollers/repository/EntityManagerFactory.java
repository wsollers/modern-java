package dev.wsollers.domain;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

  public static EntityManager getEntityManager() {
    return Persistence.createEntityManagerFactory("citizenPersistenceUnit").createEntityManager();
  }

}
