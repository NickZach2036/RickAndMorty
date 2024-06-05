package org.example.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RickAndMorty {
    private int versionNumber;
    private int powerUps;
    private boolean isPrime;

    public RickAndMorty(int versionNumber, boolean isPrime) {
        this.versionNumber = versionNumber;
        this.isPrime = isPrime;
        if(isPrime){
            this.powerUps = 8;
        }
        else{
            this.powerUps = 1;
        }
    }

    public void collectPowerUps(){
        this.powerUps++;
    }
}
