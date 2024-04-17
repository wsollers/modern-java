package dev.wsollers.repository;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomEntityManagerFactory {
  
  public static final String CITIZEN_PERSISTENCE_UNIT = "citizenPersistenceUnit";

  public static final String JAVAX_PERSISTENCE_JDBC_USER = "javax.persistence.jdbc.user";
  public static final String JAVAX_PERSISTENCE_JDBC_PASSWORD = "javax.persistence.jdbc.password";

  public static EntityManager getEntityManager() {

    //TODO Go get these securely
    String user = "postgres";
    String password = "postgres";

    Map<String, String> properties = new HashMap<>();
    properties.put(JAVAX_PERSISTENCE_JDBC_USER, user);
    properties.put(JAVAX_PERSISTENCE_JDBC_PASSWORD, password);


    EntityManagerFactory emf = Persistence.createEntityManagerFactory(CITIZEN_PERSISTENCE_UNIT,properties);
    EntityManager em = emf.createEntityManager();
    return em;

  }

}
