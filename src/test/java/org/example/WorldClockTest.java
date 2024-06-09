package org.example;

import org.example.Models.World;
import org.example.Services.Competition;
import org.example.Threads.WorldClock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorldClockTest {

    @Test
    public void testChangeWorld() {
        Competition competition = new Competition();
        WorldClock worldClock = new WorldClock(competition);

        World initialWorld = worldClock.getCurrentWorld();
        worldClock.changeWorld();
        World newWorld = worldClock.getCurrentWorld();

        assertNotEquals(initialWorld, newWorld);
    }

    @Test
    public void testStopClock() {
        Competition competition = new Competition();
        WorldClock worldClock = new WorldClock(competition);

        worldClock.stopClock();
        assertFalse(worldClock.isRunning());
    }
}
