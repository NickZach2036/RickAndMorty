package org.example.Models;

import org.example.Threads.RickAndMorty;

public class World {
    private final String name;
    private boolean flag;
    private int gems;
    private boolean hasChallenge;

    public World(String name, int gems, boolean hasChallenge) {
        this.name = name;
        this.flag = false;
        this.gems = gems;
        this.hasChallenge = hasChallenge;
    }

    public synchronized boolean takeFlag(RickAndMorty instance) {
        if (!flag) {
            flag = true;
            return true;
        } else {
            return false;
        }
    }

    public synchronized void releaseFlag() {
        flag = false;
    }

    public synchronized int collectGems() {
        if (gems > 0) {
            gems--;
            return 1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getGems() {
        return gems;
    }

    public boolean hasChallenge() {
        return hasChallenge;
    }
}
