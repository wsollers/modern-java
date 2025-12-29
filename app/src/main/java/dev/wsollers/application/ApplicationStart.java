package dev.wsollers.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;

import org.slf4j.Logger;

import dev.wsollers.logging.LogFactory;

@SpringBootApplication(scanBasePackages = "dev.wsollers")
public class ApplicationStart {

  private final static Logger logger = LogFactory.getLogger(ApplicationStart.class);

  public final static String APPLICATION_NAME = "Citizen Management System";

  public static void main(String[] args) {

    logger.info(APPLICATION_NAME + " starting...");


  }

}  

