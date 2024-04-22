package dev.wsollers.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import dev.wsollers.domain.Citizen;


public class CustomEntityManagerFactoryTest {

  @Test
  void canCreateEntityManagerXX() {
    CustomEntityManagerFactory customEntityManagerFactory = new CustomEntityManagerFactory();
    assertNotNull(customEntityManagerFactory);
  }

  @Test
   public void testConnection() {

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    Citizen citizen = em.find(Citizen.class, "1");

    assertNull(citizen);

  }

  @Test
  public void testNativeSqlQuery() {

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    Long count = ((Number )em.createNativeQuery("SELECT count(1) from \"CITIZENS\".\"CITIZEN\"")
                          .getSingleResult()).longValue();

    assertEquals(count , 0L);

  }

  @Test
  public void testJpaQlQuery() {

    EntityManager em = CustomEntityManagerFactory.getEntityManager();


    List<Citizen> citizens = em.createQuery("SELECT c FROM Citizen c", Citizen.class).getResultList();

    assertTrue(citizens.size() == 0);

  }

  @Test
  public  void testFindById() {

    EntityManager em = CustomEntityManagerFactory.getEntityManager();

    Citizen citizen = em.find(Citizen.class, "1");

    assertNull(citizen);
  } 

}

