package org.example.Services;

import org.example.Enum.Worlds;
import org.example.Models.World;
import org.example.Threads.RickAndMorty;
import org.example.Threads.WorldClock;

import java.util.*;
import java.util.concurrent.*;

public class Competition {
    private final int MAX_INSTANCES = 20;
    private final int ACTIVE_INSTANCES = 5;
    private final List<RickAndMorty> allInstances;
    private final List<RickAndMorty> activeInstances;
    private final WorldClock[] worldClocks;
    private boolean raceFinished = false;

    public Competition() {
        worldClocks = new WorldClock[5];
        for (int i = 0; i < 5; i++) {
            worldClocks[i] = new WorldClock(this);
        }
        allInstances = new ArrayList<>();
        activeInstances = new ArrayList<>();
        initializeInstances();
        initializeActiveInstances();
    }

    private void initializeInstances() {
        for (int i = 0; i < MAX_INSTANCES; i++) {
            WorldClock worldClock = worldClocks[i % 5];
            allInstances.add(new RickAndMorty(i, this, worldClock));
        }
    }

    private void initializeActiveInstances() {
        Collections.shuffle(allInstances);
        for (int i = 0; i < ACTIVE_INSTANCES; i++) {
            activeInstances.add(allInstances.remove(0));
        }
    }

    public void startCompetition() {
        for (WorldClock worldClock : worldClocks) {
            worldClock.start();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(ACTIVE_INSTANCES);
        for (RickAndMorty instance : activeInstances) {
            executorService.execute(instance);
        }
        executorService.shutdown();

        new Thread(() -> {
            while (!raceFinished) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                displayStatus();
            }
        }).start();
    }

    private void displayStatus() {
        System.out.println("Current Status of Rick and Morty Instances:");
        System.out.println("--------------------------------------------------");
        for (RickAndMorty instance : activeInstances) {
            System.out.printf("Rick and Morty %d at %s - Collected %d gems\n",
                    instance.getVersion(), instance.getCurrentWorld().getName(), instance.getCollectedGems());
        }
        System.out.println("--------------------------------------------------");
    }

    public synchronized void handleConflict(RickAndMorty instance, World world) {
        if (!world.takeFlag()) {
            instance.stopInstance();
            System.out.printf("Rick and Morty %d were killed by another instance at %s\nRemaining: %d gems\n", instance.getVersion(), world.getName(), instance.getCurrentWorld().getGems());
            replaceInstance(instance);
        }
    }

    private synchronized void replaceInstance(RickAndMorty instance) {
        activeInstances.remove(instance);

        if (!allInstances.isEmpty()) {
            RickAndMorty newActiveInstance = allInstances.remove(0);
            activeInstances.add(newActiveInstance);
            newActiveInstance.start();
            System.out.println("Started Rick and Morty " + newActiveInstance.getVersion());
        }
    }

    public World getRandomWorld() {
        return Worlds.values()[new Random().nextInt(Worlds.values().length)].getWorld();
    }

    public synchronized void finishRace() {
        raceFinished = true;
        for (WorldClock worldClock : worldClocks) {
            worldClock.stopClock();
        }
        for (RickAndMorty instance : activeInstances) {
            instance.stopInstance();
        }
        System.out.println("Race finished. Program is stopping.");
        System.exit(0);
    }
}
