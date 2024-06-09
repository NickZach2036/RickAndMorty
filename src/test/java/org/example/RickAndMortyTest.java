package org.example;

import org.example.Models.World;
import org.example.Services.Competition;
import org.example.Threads.RickAndMorty;
import org.example.Threads.WorldClock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RickAndMortyTest {

    @Test
    public void testTravelAndCollect() {
        Competition competition = new Competition();
        WorldClock worldClock = competition.getWorldClocks()[0];
        RickAndMorty instance = new RickAndMorty(1, competition, worldClock);

        instance.travelAndCollect();
        World world = instance.getCurrentWorld();

        assertNotNull(world);
        assertEquals(1, instance.getCollectedGems());
    }

    @Test
    public void testStopInstance() {
        Competition competition = new Competition();
        WorldClock worldClock = competition.getWorldClocks()[0];
        RickAndMorty instance = new RickAndMorty(1, competition, worldClock);

        instance.stopInstance();
        assertFalse(instance.isRunning());
    }
}
