package dev.wsollers.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;

import org.slf4j.Logger;

import dev.wsollers.logging.LogFactory;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dev.wsollers")
@EnableJpaRepositories(basePackages = "dev.wsollers.northwinds.repository")
public class ApplicationStart {

  private final static Logger logger = LogFactory.getLogger(ApplicationStart.class);

  public final static String APPLICATION_NAME = "Citizen Management System";

  public static void main(String[] args) {

    logger.info(APPLICATION_NAME + " starting...");
    SpringApplication.run(ApplicationStart.class, args);

  }

}  

