package org.example.Threads;

import org.example.Models.World;
import org.example.Services.Competition;

import java.util.Random;

public class WorldClock extends Thread {
    private final World[] worlds;
    private boolean running;
    private Competition competition;
    private int clockId;

    public WorldClock(World[] worlds, Competition competition, int clockId) {
        this.worlds = worlds;
        this.running = true;
        this.competition = competition;
        this.clockId = clockId;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running) {
            try {
                Thread.sleep(3000); // Change world every 3 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            changeWorlds();
        }
    }

    private void changeWorlds() {
        World newWorld = worlds[new Random().nextInt(worlds.length)];
        for (RickAndMorty instance : competition.getActiveInstances()) {
            if (instance.getId() % 5 == clockId) {
                instance.setCurrentWorld(newWorld);
                System.out.printf("Rick and Morty %d are on %s\n", instance.getId(), newWorld.getName());
            }
        }
    }

    public void stopClock() {
        running = false;
    }
}
