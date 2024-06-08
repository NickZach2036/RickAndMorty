package org.example;

import org.example.Models.SharedGems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SharedGemsTest {
    private SharedGems sharedGems;

    @BeforeEach
    void setUp() {
        sharedGems = new SharedGems(10);
    }

    @Test
    void testCollectGems() {
        assertTrue(sharedGems.collectGems());
        assertEquals(9, sharedGems.getGemsCount());
    }

    @Test
    void testAddGems() {
        sharedGems.addGems(5);
        assertEquals(15, sharedGems.getGemsCount());
    }
}
