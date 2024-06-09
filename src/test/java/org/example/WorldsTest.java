package org.example;

import org.example.Enum.Worlds;
import org.example.Models.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldsTest {

    @Test
    public void testGetName() {
        assertEquals("Earth C-137", Worlds.EARTH_C137.getName());
    }

    @Test
    public void testGetGems() {
        assertEquals(5, Worlds.EARTH_C137.getGems());
    }

    @Test
    public void testGetWorld() {
        World world = Worlds.EARTH_C137.getWorld();
        assertEquals("Earth C-137", world.getName());
        assertEquals(5, world.getGems());
    }
}
