package org.example.Enum;

import org.example.Models.World;

public enum Worlds {
    EARTH_C137("Earth C-137", 5, false),
    DIMENSION_C_500A("Dimension C-500A", 3, true),
    FROOPYLAND("Froopyland", 7, false),
    GAZORPAZORP("GazorpaZorp", 4, true),
    HEADQUARTERS("Headquarters", 10, false),
    PLUMBUSLAND("Plumbusland", 2, true),
    BLITZ_AND_CHITZ("Blitz and Chitz", 6, false),
    NUKE_NUCLEON("Nuke Nucleon", 8, true),
    SQUANCHY_WORLD("Squanchy World", 5, false),
    MICROVERSE("Microverse", 3, true),
    DOOFUS_DIMENSION("Doofus Dimension", 6, false),
    EVIL_RICK_UNIVERSE("Evil Rick Universe", 7, true),
    INTERDIMENSIONAL_CABLE("Interdimensional Cable", 4, false),
    SCARY_TERRY_DREAM("Scary Terry Dream", 3, true),
    CROMULON_DIMENSION("Cromulon Dimension", 8, false),
    GLOOTIE_PLANET("Glootie Planet", 2, true),
    TOILET_WORLD("Toilet World", 5, false),
    BATTERY_MICROVERSE("Battery Microverse", 6, true),
    PHEONIX_PERSON_WORLD("Phoenix Person World", 7, false),
    MORTY_MIND_BLOWERS("Morty Mind Blowers", 3, true),
    RICK_SHANK_UNIVERSE("Rick Shank Universe", 9, false),
    ZEPPY_ZEPPIAN("Zeppy Zeppian", 4, true),
    MEESEEKS_BOX("Meeseeks Box", 5, false),
    PICKLE_RICK_REALM("Pickle Rick Realm", 6, true),
    HELL_DIMENSION("Hell Dimension", 7, false),
    ANT_EYE_WORLD("Ant Eye World", 4, true),
    TIME_TRAVEL_HEIST("Time Travel Heist", 5, false),
    TOOTHPICK_WORLD("Toothpick World", 3, true),
    SMITH_FAMILY_UNIVERSE("Smith Family Universe", 4, false),
    FEDERAL_GOVERNMENT("Federal Government", 6, true),
    TURKEY_DIMENSION("Turkey Dimension", 3, false),
    GALACTIC_FEDERATION("Galactic Federation", 7, true),
    WONG_PLANET("Wong Planet", 2, false),
    PARASITE_DIMENSION("Parasite Dimension", 6, true),
    CYBERNETIC_SUN("Cybernetic Sun", 4, false),
    INFINITY_POOL("Infinity Pool", 5, true),
    THE_RICK_LANTIS_MIXUP("The Ricklantis Mixup", 3, false),
    MORTY_TOWN("Morty Town", 4, true),
    ANIMAL_CROSSING("Animal Crossing", 6, false),
    EVIL_SMITH_HOUSE("Evil Smith House", 5, true),
    PUMPKIN_SEED("Pumpkin Seed", 3, false),
    MAD_MAX_WORLD("Mad Max World", 4, true),
    STAR_WARS_DIMENSION("Star Wars Dimension", 7, false),
    WUBBA_LUBBA_DUB_DUB("Wubba Lubba Dub Dub", 2, true),
    INTERGALACTIC_WAR("Intergalactic War", 6, false),
    AQUA_RICK("Aqua Rick", 4, true),
    LAZY_RICK("Lazy Rick", 5, false),
    GHOST_IN_A_JAR("Ghost in a Jar", 3, true),
    SCHWIFTY_LAND("Schwifty Land", 7, false);

    private final String name;
    private final int gems;
    private final boolean hasChallenge;
    private final World world;

    Worlds(String name, int gems, boolean hasChallenge) {
        this.name = name;
        this.gems = gems;
        this.hasChallenge = hasChallenge;
        this.world = new World(name, gems, hasChallenge);
    }

    public String getName() {
        return name;
    }

    public int getInitialGems() {
        return gems;
    }

    public World getWorld() {
        return world;
    }

    public boolean hasChallenge() {
        return hasChallenge;
    }
}
