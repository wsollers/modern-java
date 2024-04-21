package dev.wsollers.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomEntityManagerFactoryTest {

  @Test
  void canCreateEntityManager() {
    CustomEntityManagerFactory customEntityManagerFactory = new CustomEntityManagerFactory();
    assertNotNull(customEntityManagerFactory);
  }
}

