package org.example;

import org.example.Models.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    private World world;

    @BeforeEach
    void setUp() {
        //
    }

    @Test
    void testGetName() {
        assertEquals("Test World", world.getName());
    }

    @Test
    void testGetGems() {
        assertEquals(5, world.getGems());
    }

    @Test
    void testCollectGems() {
        int collected = world.collectGems();
        assertEquals(1, collected);
        assertEquals(4, world.getGems());
    }

    @Test
    void testFlag() {
        //
    }
}
