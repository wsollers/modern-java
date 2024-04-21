package dev.wsollers.application;

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import dev.wsollers.repository.CustomEntityManagerFactory;
import dev.wsollers.repository.CitizenRepository;
import dev.wsollers.repository.CitizenJsonRepository;
import dev.wsollers.logging.LogFactory;
import dev.wsollers.repository.QueryTester;

public class ApplicationStart {

  private final static Logger logger = LogFactory.getLogger(ApplicationStart.class);

  public final static String APPLICATION_NAME = "Citizen Management System";

  public static void main(String[] args) {

    logger.info(APPLICATION_NAME + " starting...");

    CitizenRepository citizenRepository = new CitizenJsonRepository();
    citizenRepository.getCitizens();
    LogFactory.testLogger();
    EntityManager em = CustomEntityManagerFactory.getEntityManager();
    QueryTester.testConnection();
    QueryTester.testSqlQuery();
    QueryTester.testQuery();
    QueryTester.testFind();
  }

}  

