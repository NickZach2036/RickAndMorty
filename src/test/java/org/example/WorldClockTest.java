package org.example;

import org.example.Enum.Worlds;
import org.example.Services.Competition;
import org.example.Threads.WorldClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldClockTest {
    private WorldClock worldClock;
    private Competition competition;

    @BeforeEach
    void setUp() {
        //
    }

    @Test
    void testChangeWorlds() {
        worldClock.start();
        try {
            Thread.sleep(5000); // Wait for world change (assuming change happens within 5 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(worldClock);
    }

    @Test
    void testStopClock() {
        //
    }
}
