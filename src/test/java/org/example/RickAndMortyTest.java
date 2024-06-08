package org.example;

import org.example.Enum.Worlds;
import org.example.Services.Competition;
import org.example.Threads.RickAndMorty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RickAndMortyTest {
    private RickAndMorty rickAndMorty;
    private Competition competition;

    @BeforeEach
    void setUp() {
        competition = new Competition();
        rickAndMorty = new RickAndMorty(1, competition);
    }

    @Test
    void testTravelAndCollect() {
        rickAndMorty.setCurrentWorld(Worlds.EARTH_C137.getWorld());
        int initialGems = Worlds.EARTH_C137.getInitialGems();
        int gemsCollected = rickAndMorty.getCollectedGems();
        rickAndMorty.travelAndCollect();
        assertEquals(gemsCollected + 1, rickAndMorty.getCollectedGems());
    }

    @Test
    void testStopInstance() {
        //
    }
}
