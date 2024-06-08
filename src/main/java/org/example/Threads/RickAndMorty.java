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
    private boolean flag;

    public RickAndMorty(int id, Competition competition) {
        this.id = id;
        this.running = true;
        this.collectedGems = 0;
        this.competition = competition;
        this.flag = false;
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

    public void travelAndCollect() {
        if (currentWorld != null) {
            synchronized (currentWorld) {
                if (currentWorld.isFlag() && !flag) {
                    competition.handleConflict(this, currentWorld);
                } else if (!currentWorld.isFlag()) {
                    currentWorld.takeFlag(this);
                    flag = true;
                    collectedGems += currentWorld.collectGems();
                    System.out.printf("Rick and Morty %d are on %s:\nCollected 1 gem\nRemaining %d\n", id, currentWorld.getName(), currentWorld.getGems());
                    currentWorld.releaseFlag();
                    flag = false;

                    if (currentWorld.getName().equals("Headquarters")) {
                        competition.finishRace(this);
                    }
                }
            }
        }

        currentWorld = competition.getRandomWorld();
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
