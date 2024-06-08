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
    private WorldClock[] worldClocks;
    private boolean raceFinished = false;
    private World[] worlds;

    public Competition() {
        this.worlds = Arrays.stream(Worlds.values()).map(Worlds::getWorld).toArray(World[]::new);
        this.worldClocks = new WorldClock[ACTIVE_INSTANCES];
        for (int i = 0; i < ACTIVE_INSTANCES; i++) {
            worldClocks[i] = new WorldClock(worlds, this, i);
        }
        allInstances = new ArrayList<>();
        activeInstances = new ArrayList<>();
        initializeInstances();
        initializeActiveInstances();
    }

    private void initializeInstances() {
        for (int i = 0; i < MAX_INSTANCES; i++) {
            allInstances.add(new RickAndMorty(i + 1, this)); // Уникални ID-та
        }
    }

    private void initializeActiveInstances() {
        Collections.shuffle(allInstances);
        for (int i = 0; i < ACTIVE_INSTANCES; i++) {
            RickAndMorty instance = allInstances.remove(0);
            instance.setCurrentWorld(worlds[i]);
            activeInstances.add(instance);
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
                    Thread.sleep(3000); // Update every 3 seconds
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
            if (instance.getCurrentWorld() != null) {
                System.out.printf("Rick and Morty %d are on %s\n", instance.getId(), instance.getCurrentWorld().getName());
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public synchronized List<RickAndMorty> getActiveInstances() {
        return new ArrayList<>(activeInstances); // Връща копие, за да избегнем ConcurrentModificationException
    }

    public synchronized void handleConflict(RickAndMorty instance, World world) {
        if (!world.takeFlag(instance)) {
            instance.stopInstance();
            System.out.printf("Rick and Morty %d killed by another instance at %s\n", instance.getId(), world.getName());
            changeActiveInstances();
        } else if (instance.getId() == 1) {
            System.out.printf("Rick Prime (%d) killed another instance at %s.\n", instance.getId(), world.getName());
        }
    }

    public World getRandomWorld() {
        return worlds[new Random().nextInt(worlds.length)];
    }

    public synchronized void changeActiveInstances() {
        Random random = new Random();
        int stopIndex = random.nextInt(ACTIVE_INSTANCES);
        activeInstances.get(stopIndex).stopInstance();
        System.out.println("Stopped Rick and Morty " + activeInstances.get(stopIndex).getId());

        if (!allInstances.isEmpty()) {
            int startIndex = random.nextInt(allInstances.size());
            RickAndMorty newActiveInstance = allInstances.remove(startIndex);
            newActiveInstance.setCurrentWorld(worlds[startIndex % worlds.length]);
            activeInstances.set(stopIndex, newActiveInstance);
            newActiveInstance.start();
            System.out.println("Started Rick and Morty " + newActiveInstance.getId());
        }
    }

    public synchronized void finishRace(RickAndMorty winner) {
        raceFinished = true;
        for (WorldClock worldClock : worldClocks) {
            worldClock.stopClock();
        }
        System.out.printf("Rick and Morty %d have reached the headquarters and won the race!\n", winner.getId());
        for (RickAndMorty instance : activeInstances) {
            instance.stopInstance();
        }
    }
}
