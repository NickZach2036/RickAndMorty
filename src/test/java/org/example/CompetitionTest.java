package org.example;

import org.example.Enum.Worlds;
import org.example.Models.World;
import org.example.Services.Competition;
import org.example.Threads.RickAndMorty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompetitionTest {
    private Competition competition;

    @BeforeEach
    void setUp() {
        competition = new Competition();
    }

    @Test
    void testGetRandomWorld() {
        World randomWorld = competition.getRandomWorld();
        assertNotNull(randomWorld);
    }

    @Test
    void testChangeActiveInstances() {
        List<RickAndMorty> activeInstancesBefore = competition.getActiveInstances();
        competition.changeActiveInstances();
        List<RickAndMorty> activeInstancesAfter = competition.getActiveInstances();
        assertNotEquals(activeInstancesBefore, activeInstancesAfter);
    }

    @Test
    void testHandleConflict() {
        RickAndMorty instance1 = new RickAndMorty(1, competition);
        RickAndMorty instance2 = new RickAndMorty(2, competition);
        instance1.setCurrentWorld(Worlds.EARTH_C137.getWorld());
        instance2.setCurrentWorld(Worlds.EARTH_C137.getWorld());

        //
    }

    @Test
    void testFinishRace() {
        //
    }
}
