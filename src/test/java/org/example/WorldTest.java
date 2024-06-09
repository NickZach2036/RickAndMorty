package org.example;

import org.example.Models.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    @Test
    public void testTakeFlag() {
        World world = new World("Test World", 5);
        assertTrue(world.takeFlag());
        assertFalse(world.takeFlag());
    }

    @Test
    public void testReleaseFlag() {
        World world = new World("Test World", 5);
        world.takeFlag();
        world.releaseFlag();
        assertTrue(world.takeFlag());
    }

    @Test
    public void testCollectGem() {
        World world = new World("Test World", 2);
        assertEquals(1, world.collectGem());
        assertEquals(1, world.collectGem());
        assertEquals(0, world.collectGem());
    }

    @Test
    public void testGetName() {
        World world = new World("Test World", 5);
        assertEquals("Test World", world.getName());
    }

    @Test
    public void testGetGems() {
        World world = new World("Test World", 5);
        assertEquals(5, world.getGems());
    }
}
