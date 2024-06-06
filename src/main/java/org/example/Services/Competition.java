package org.example.Services;

import org.example.Enum.Worlds;
import org.example.Models.RickAndMorty;
import java.util.*;
import java.util.concurrent.*;


public class Competition {
    private final int MAX_INSTANCES = 20;
    private final int ACTIVE_INSTANCES = 5;
    private List<RickAndMorty> allInstances;
    private List<RickAndMorty> activeInstances;
    private ConcurrentMap<Worlds, RickAndMorty> worldOccupancy;


    public Competition() {
        allInstances = new ArrayList<>();
        activeInstances = new ArrayList<>();
        worldOccupancy = new ConcurrentHashMap<>();
        initializeInstances();
        initializeActiveInstances();
    }

    private void initializeInstances() {
        Random random = new Random();
        for (int i = 0; i < MAX_INSTANCES; i++) {
            int versionNumber = random.nextInt(10000) + 1;
            boolean isPrime = versionNumber == 1;
            allInstances.add(new RickAndMorty(versionNumber, isPrime));
        }
    }

    private void initializeActiveInstances() {
        Collections.shuffle(allInstances);
        for (int i = 0; i < ACTIVE_INSTANCES; i++) {
            activeInstances.add(allInstances.remove(0));
        }
    }

    boolean resolveEncounter(RickAndMorty instance1, RickAndMorty instance2) throws Exception {
        Random random = new Random();
        if (instance1.getPowerUps() > instance2.getPowerUps()) {
            if (!allInstances.isEmpty()) {
                activeInstances.remove(instance2);
                activeInstances.add(allInstances.remove(0));
            } else {
                throw new Exception("No more instances available");
            }
        } else {
            if (!allInstances.isEmpty()) {
                activeInstances.remove(instance1);
                activeInstances.add(allInstances.remove(0));
            } else {
                throw new Exception("No more instances available");
            }
        }
        return true;
    }

    public void startCompetition() {
        ExecutorService executorService = Executors.newFixedThreadPool(ACTIVE_INSTANCES);
        for (RickAndMorty instance : activeInstances) {
            executorService.execute(new Journey(instance, worldOccupancy, this));
        }
        executorService.shutdown();
    }
}
