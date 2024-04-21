package dev.wsollers.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CitizenTest {

  @Test
  void testConstructor() {
    Citizen citizen = new Citizen();
    assertNotNull(citizen);
  }


}
