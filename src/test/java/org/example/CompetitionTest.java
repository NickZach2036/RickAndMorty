package org.example;

import org.example.Models.World;
import org.example.Services.Competition;
import org.example.Threads.RickAndMorty;
import org.example.Threads.WorldClock;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompetitionTest {

    @Test
    public void testInitialization() {
        Competition competition = new Competition();
        System.out.println("All Instances: " + competition.getAllInstances().size());  // Debugging line
        System.out.println("Active Instances: " + competition.getActiveInstances().size());  // Debugging line
        assertEquals(20, competition.getAllInstances().size());
        assertEquals(5, competition.getActiveInstances().size());
    }

    @Test
    public void testGetRandomWorld() {
        Competition competition = new Competition();
        World world = competition.getRandomWorld();
        assertNotNull(world);
    }

    @Test
    public void testHandleConflict() {
        Competition competition = new Competition();
        RickAndMorty instance = competition.getActiveInstances().get(0);
        World world = competition.getRandomWorld();
        competition.handleConflict(instance, world);
        assertFalse(instance.isRunning());
    }
}
