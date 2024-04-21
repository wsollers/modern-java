package dev.wsollers.repository;

import dev.wsollers.domain.Citizen;
import dev.wsollers.logging.LogFactory;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

public class QueryTester {

  private static Logger logger = LogFactory.getLogger(QueryTester.class);

  public static boolean testConnection() {

    logger.info("Testing connection...");

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    em.createNativeQuery("SELECT 1").getSingleResult();

    logger.info("Testing connection Successful");

    return true;
  }

  public static boolean testSqlQuery() {

    logger.info("Testing SQL Query...");

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    em.createNativeQuery("SELECT count(1) from \"CITIZENS\".\"CITIZEN\"").getSingleResult();

    logger.info("Testing SQL Successful");

    return true;
  }

  public static boolean testQuery() {

    logger.info("Attempting a fetch all query thru jpql...");

    EntityManager em = CustomEntityManagerFactory.getEntityManager();


    em.createQuery("SELECT c FROM Citizen c").getResultList();

    logger.info("Query successful!");

    return true;
  }

  public static boolean testFind() {

    logger.info("Attempting an EntityManager find");

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    Citizen citizen = em.find(Citizen.class, "1");

    logger.info("Query successful!");

    return true;
  } 

}
