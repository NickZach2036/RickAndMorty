package org.example.Threads;

import org.example.Models.World;
import org.example.Services.Competition;
import org.example.Threads.WorldClock;

import java.util.Random;

public class RickAndMorty extends Thread {
    private final int id;
    private boolean running;
    private int collectedGems;
    private final Competition competition;
    private World currentWorld;
    private final WorldClock worldClock;

    public RickAndMorty(int id, Competition competition, WorldClock worldClock) {
        this.id = id;
        this.running = true;
        this.collectedGems = 0;
        this.competition = competition;
        this.worldClock = worldClock;
        this.currentWorld = worldClock.getCurrentWorld();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(2800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            travelAndCollect();
        }
    }

    private synchronized void travelAndCollect() {
        currentWorld = worldClock.getCurrentWorld();
        synchronized (currentWorld) {
            if (!currentWorld.isFlag()) {
                currentWorld.takeFlag();
                int collected = currentWorld.collectGem();
                collectedGems += collected;
                System.out.printf("Rick and Morty %d are on %s:\nCollected 1 gem\nRemaining %d gems\n", id, currentWorld.getName(), currentWorld.getGems());

                if(currentWorld.getName().equals("Headquarters")) {
                    competition.finishRace();
                }
            } else {
                competition.handleConflict(this, currentWorld);
            }
        }
    }

    public void stopInstance() {
        running = false;
    }

    public int getCollectedGems() {
        return collectedGems;
    }

    public int getVersion() {
        return id;
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

}
