package org.example.Services;

import org.example.Enum.Worlds;
import org.example.Models.RickAndMorty;

import java.util.Random;
import java.util.concurrent.ConcurrentMap;

public class Journey implements Runnable {
    private RickAndMorty instance;
    private ConcurrentMap<Worlds, RickAndMorty> worldOccupancy;
    private Competition competition;

    public Journey(RickAndMorty instance, ConcurrentMap<Worlds, RickAndMorty> worldOccupancy, Competition competition) {
        this.instance = instance;
        this.worldOccupancy = worldOccupancy;
        this.competition = competition;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            Worlds currentWorld = Worlds.values()[random.nextInt(Worlds.values().length)];

            // Simulate travel time
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            synchronized (worldOccupancy) {
                RickAndMorty occupant = worldOccupancy.get(currentWorld);
                if (occupant == null) {
                    worldOccupancy.put(currentWorld, instance);
                    instance.collectPowerUps();
                    currentWorld.decreasePowerUps();
                } else if (!occupant.equals(instance)) {
                    // Encounter logic
                    try {
                        if (competition.resolveEncounter(instance, occupant)) {
                            worldOccupancy.put(currentWorld, instance);
                            instance.collectPowerUps();
                            currentWorld.decreasePowerUps();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
