package org.example.Models;

public class World {
    private final String name;
    private boolean flag;
    private int gems;

    public World(String name, int gems) {
        this.name = name;
        this.flag = false;
        this.gems = gems;
    }

    public synchronized boolean takeFlag() {
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

    public synchronized int collectGem() {
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
}
