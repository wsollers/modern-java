package dev.wsollers.northwinds.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsStateTest {


    @Test
    void testConstructor() {
        UsState state = new UsState();
        assertNotNull(state);
    }



}



