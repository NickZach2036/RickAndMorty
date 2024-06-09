package org.example.Threads;

import org.example.Models.World;
import org.example.Services.Competition;

public class WorldClock extends Thread {
    private World currentWorld;
    private boolean running;
    private final Competition competition;

    public WorldClock(Competition competition) {
        this.competition = competition;
        this.currentWorld = competition.getRandomWorld();
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            changeWorld();
        }
    }

    private void changeWorld() {
        synchronized (currentWorld) {
            currentWorld.releaseFlag();
        }
        currentWorld = competition.getRandomWorld();
        /*if (currentWorld.getName().equals("Headquarters")) {
            competition.finishRace();
        }*/
    }

    public synchronized World getCurrentWorld() {
        return currentWorld;
    }

    public void stopClock() {
        running = false;
    }
}
