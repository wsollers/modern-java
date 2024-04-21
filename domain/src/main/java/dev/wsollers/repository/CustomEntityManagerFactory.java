package dev.wsollers.repository;

import dev.wsollers.logging.LogFactory;

import org.slf4j.Logger;

import java.util.Properties;
import java.util.Map;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * CustomEntityManagerFactory
 *
 * This class is responsible for creating an EntityManager
 * Remeber that PostgreSQL is the database being used
 * and it has a weird rule about quoted identifiers. 
 * The persistence.xml file has a property to handle this.
 *
 * Also check out the QueryTester class for some examples of 
 * Native Queries and how to quote the identifiers.
 *
 * Also Postgres is a little weird about case sensitivity.
 */
public class CustomEntityManagerFactory {

  private static final Logger logger = LogFactory.getLogger(CustomEntityManagerFactory.class);
  
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
    logger.info("EntityManager created");
    return em;

  }

}
