package org.example;

import org.example.Models.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    private World world;

    @BeforeEach
    void setUp() {
        world = new World("Test World", 5, true);
    }

    @Test
    void testTakeFlag() {
        assertTrue(world.takeFlag(null));
        assertFalse(world.takeFlag(null));
    }

    @Test
    void testCollectGems() {
        int initialGems = world.getGems();
        int gemsCollected = world.collectGems();
        assertEquals(initialGems - 1, world.getGems());
        assertEquals(1, gemsCollected);
    }
}
