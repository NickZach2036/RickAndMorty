package org.example.Models;

public class SharedGems {
    private int gemsCount;

    public SharedGems(int initialGems) {
        this.gemsCount = initialGems;
    }

    public synchronized boolean collectGems() {
        if (gemsCount > 0) {
            gemsCount--;
            return true;
        }
        return false;
    }

    public synchronized int getGemsCount() {
        return gemsCount;
    }

    public synchronized void addGems(int gems) {
        gemsCount += gems;
    }
}
