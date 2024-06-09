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
    void initializeInstances() {
        //
    }

    @Test
    void initializeActiveInstances() {
        assertEquals(5, competition.getActiveInstances().size());
    }

    @Test
    void getRandomWorld() {
        World world = competition.getRandomWorld();
        assertNotNull(world);
    }

    @Test
    void handleConflict() {
        RickAndMorty instance1 = new RickAndMorty(1, competition);
        RickAndMorty instance2 = new RickAndMorty(2, competition);
        instance1.setCurrentWorld(Worlds.CYBERNETIC_SUN.getWorld());
        instance2.setCurrentWorld(Worlds.CYBERNETIC_SUN.getWorld());

        competition.getActiveInstances().add(instance1);
        competition.getActiveInstances().add(instance2);

        competition.handleConflict(instance1, instance1.getCurrentWorld());

        //
    }

    @Test
    void changeActiveInstances() {
        int initialSize = competition.getActiveInstances().size();
        competition.changeActiveInstances();
        assertEquals(initialSize, competition.getActiveInstances().size());
    }

    @Test
    void finishRace() {
        //
    }
}
