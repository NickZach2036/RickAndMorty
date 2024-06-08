package org.example.Threads;

import org.example.Models.World;

import java.util.Random;

public class WorldClock extends Thread {
    private final World[] worlds;
    private boolean running;

    public WorldClock(World[] worlds) {
        this.worlds = worlds;
        this.running = true;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running) {
            try {
                Thread.sleep(5000); // Change world every 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            changeWorlds();
        }
    }

    private void changeWorlds() {
        for (World world : worlds) {
            synchronized (world) {
                world.releaseFlag();
            }
        }
        System.out.println("Worlds have changed.");
    }

    public void stopClock() {
        running = false;
    }
}
