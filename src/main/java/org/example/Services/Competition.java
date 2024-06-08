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
    private List<RickAndMorty> allInstances;
    private List<RickAndMorty> activeInstances;
    private WorldClock worldClock;
    private boolean raceFinished = false;
    private World[] worlds;

    public Competition() {
        this.worlds = Arrays.stream(Worlds.values()).map(Worlds::getWorld).toArray(World[]::new);
        this.worldClock = new WorldClock(worlds);
        allInstances = new ArrayList<>();
        activeInstances = new ArrayList<>();
        initializeInstances();
        initializeActiveInstances();
    }

    private void initializeInstances() {
        for (int i = 0; i < MAX_INSTANCES; i++) {
            allInstances.add(new RickAndMorty(i, this));
        }
    }

    private void initializeActiveInstances() {
        Collections.shuffle(allInstances);
        for (int i = 0; i < ACTIVE_INSTANCES; i++) {
            activeInstances.add(allInstances.remove(0));
        }
    }

    public void startCompetition() {
        worldClock.start();
        ExecutorService executorService = Executors.newFixedThreadPool(ACTIVE_INSTANCES);
        for (RickAndMorty instance : activeInstances) {
            executorService.execute(instance);
        }
        executorService.shutdown();

        new Thread(() -> {
            while (!raceFinished) {
                try {
                    Thread.sleep(10000); // Change active workers every 10 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                changeActiveInstances();
                displayStatus();
            }
        }).start();
    }

    private synchronized void changeActiveInstances() {
        Random random = new Random();
        int stopIndex = random.nextInt(ACTIVE_INSTANCES);
        activeInstances.get(stopIndex).stopInstance();
        System.out.println("Stopped Rick and Morty " + activeInstances.get(stopIndex).getId());

        int startIndex = random.nextInt(allInstances.size());
        RickAndMorty newActiveInstance = allInstances.remove(startIndex);
        activeInstances.set(stopIndex, newActiveInstance);
        newActiveInstance.start();
        System.out.println("Started Rick and Morty " + newActiveInstance.getId());
    }

    private void displayStatus() {
        System.out.println("Current Status of Rick and Morty Instances:");
        System.out.println("--------------------------------------------------");
        for (RickAndMorty instance : activeInstances) {
            System.out.printf("Rick and Morty %d at %s - Collected %d gems\n",
                    instance.getId(), instance.getCurrentWorld().getName(), instance.getCollectedGems());
        }
        System.out.println("--------------------------------------------------");
    }

    public synchronized void handleConflict(RickAndMorty instance, World world) {
        if (world.takeFlag(instance)) {
            if (instance.getId() == 1) {
                System.out.printf("Rick Prime (%d) killed another instance at %s.\n", instance.getId(), world.getName());
            } else {
                instance.stopInstance();
                System.out.printf("Rick and Morty %d killed by another instance at %s\n", instance.getId(), world.getName());
                changeActiveInstances();
            }
        }
    }

    public World getRandomWorld() {
        return worlds[new Random().nextInt(worlds.length)];
    }

    public synchronized void finishRace(RickAndMorty winner) {
        raceFinished = true;
        worldClock.stopClock();
        System.out.printf("Rick and Morty %d have reached the headquarters and won the race!\n", winner.getId());
    }
}
