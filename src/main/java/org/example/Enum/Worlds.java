package org.example.Enum;

import lombok.Getter;

@Getter
public enum Worlds {
    EARTH_C137(5),
    DIMENSION_C_500A(3),
    FROOPYLAND(7),
    GAZORPAZORP(4),
    HEADQUARTERS(10),
    PLUMBUSLAND(2),
    BLITZ_AND_CHITZ(6),
    NUKE_NUCLEON(8),
    SQUANCHY_WORLD(5),
    MICROVERSE(3),
    DOOFUS_DIMENSION(6),
    EVIL_RICK_UNIVERSE(7),
    INTERDIMENSIONAL_CABLE(4),
    SCARY_TERRY_DREAM(3),
    CROMULON_DIMENSION(8),
    GLOOTIE_PLANET(2),
    TOILET_WORLD(5),
    BATTERY_MICROVERSE(6),
    PHEONIX_PERSON_WORLD(7),
    MORTY_MIND_BLOWERS(3),
    RICK_SHANK_UNIVERSE(9),
    ZEPPY_ZEPPIAN(4),
    MEESEEKS_BOX(5),
    PICKLE_RICK_REALM(6),
    HELL_DIMENSION(7),
    ANT_EYE_WORLD(4),
    TIME_TRAVEL_HEIST(5),
    TOOTHPICK_WORLD(3),
    SMITH_FAMILY_UNIVERSE(4),
    FEDERAL_GOVERNMENT(6),
    TURKEY_DIMENSION(3),
    GALACTIC_FEDERATION(7),
    WONG_PLANET(2),
    PARASITE_DIMENSION(6),
    CYBERNETIC_SUN(4),
    INFINITY_POOL(5),
    THE_RICK_LANTIS_MIXUP(3),
    MORTY_TOWN(4),
    ANIMAL_CROSSING(6),
    EVIL_SMITH_HOUSE(5),
    PUMPKIN_SEED(3),
    MAD_MAX_WORLD(4),
    STAR_WARS_DIMENSION(7),
    WUBBA_LUBBA_DUB_DUB(2),
    INTERGALACTIC_WAR(6),
    AQUA_RICK(4),
    LAZY_RICK(5),
    GHOST_IN_A_JAR(3),
    SCHWIFTY_LAND(7);

    private int powerUps;

    Worlds(int powerUps) {
        this.powerUps = powerUps;
    }

    public void decreasePowerUps(){
        powerUps--;
    }

}

