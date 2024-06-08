package org.example.Threads;

import org.example.Models.World;
import org.example.Services.Competition;

import java.util.Random;

public class RickAndMorty extends Thread {
    private int id;
    private World currentWorld;
    private boolean running;
    private int collectedGems;
    private Competition competition;

    public RickAndMorty(int id, Competition competition) {
        this.id = id;
        this.running = true;
        this.collectedGems = 0;
        this.competition = competition;
        this.currentWorld = competition.getRandomWorld();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running) {
            try {
                Thread.sleep(2000 + random.nextInt(3000)); // Travel every 2-5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            travelAndCollect();
        }
    }

    private void travelAndCollect() {
        World newWorld = competition.getRandomWorld();
        synchronized (newWorld) {
            if (!newWorld.isFlag()) {
                newWorld.takeFlag(this);
                collectedGems += newWorld.collectGems();
                if (newWorld.hasChallenge() && id != 1) {
                    System.out.printf("Rick and Morty %d faced a challenge at %s and were delayed.\n", id, newWorld.getName());
                    try {
                        Thread.sleep(2000 + new Random().nextInt(3000)); // Delay due to challenge
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.printf("Rick and Morty %d collected a gem at %s. Total collected: %d\n", id, newWorld.getName(), collectedGems);
                newWorld.releaseFlag();
                currentWorld = newWorld;
            } else {
                competition.handleConflict(this, newWorld);
            }
        }
    }

    public void stopInstance() {
        running = false;
    }

    public int getCollectedGems() {
        return collectedGems;
    }

    public long getId() {
        return id;
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
    }
}
