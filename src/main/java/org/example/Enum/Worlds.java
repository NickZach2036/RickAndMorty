package org.example.Enum;

import org.example.Models.World;

public enum Worlds {
    EARTH_C137("Earth C-137", 5),
    DIMENSION_C_500A("Dimension C-500A", 3),
    FROOPYLAND("Froopyland", 7),
    GAZORPAZORP("GazorpaZorp", 4),
    HEADQUARTERS("Headquarters", 10),
    PLUMBUSLAND("Plumbusland", 2),
    BLITZ_AND_CHITZ("Blitz and Chitz", 6),
    NUKE_NUCLEON("Nuke Nucleon", 8),
    SQUANCHY_WORLD("Squanchy World", 5),
    MICROVERSE("Microverse", 3),
    DOOFUS_DIMENSION("Doofus Dimension", 6),
    EVIL_RICK_UNIVERSE("Evil Rick Universe", 7),
    INTERDIMENSIONAL_CABLE("Interdimensional Cable", 4),
    SCARY_TERRY_DREAM("Scary Terry Dream", 3),
    CROMULON_DIMENSION("Cromulon Dimension", 8),
    GLOOTIE_PLANET("Glootie Planet", 2),
    TOILET_WORLD("Toilet World", 5),
    BATTERY_MICROVERSE("Battery Microverse", 6),
    PHEONIX_PERSON_WORLD("Phoenix Person World", 7),
    MORTY_MIND_BLOWERS("Morty Mind Blowers", 3),
    RICK_SHANK_UNIVERSE("Rick Shank Universe", 9),
    ZEPPY_ZEPPIAN("Zeppy Zeppian", 4),
    MEESEEKS_BOX("Meeseeks Box", 5),
    PICKLE_RICK_REALM("Pickle Rick Realm", 6),
    HELL_DIMENSION("Hell Dimension", 7),
    ANT_EYE_WORLD("Ant Eye World", 4),
    TIME_TRAVEL_HEIST("Time Travel Heist", 5),
    TOOTHPICK_WORLD("Toothpick World", 3),
    SMITH_FAMILY_UNIVERSE("Smith Family Universe", 4),
    FEDERAL_GOVERNMENT("Federal Government", 6),
    TURKEY_DIMENSION("Turkey Dimension", 3),
    GALACTIC_FEDERATION("Galactic Federation", 7),
    WONG_PLANET("Wong Planet", 2),
    PARASITE_DIMENSION("Parasite Dimension", 6),
    CYBERNETIC_SUN("Cybernetic Sun", 4),
    INFINITY_POOL("Infinity Pool", 5),
    THE_RICK_LANTIS_MIXUP("The Ricklantis Mixup", 3),
    MORTY_TOWN("Morty Town", 4),
    ANIMAL_CROSSING("Animal Crossing", 6),
    EVIL_SMITH_HOUSE("Evil Smith House", 5),
    PUMPKIN_SEED("Pumpkin Seed", 3),
    MAD_MAX_WORLD("Mad Max World", 4),
    STAR_WARS_DIMENSION("Star Wars Dimension", 7),
    WUBBA_LUBBA_DUB_DUB("Wubba Lubba Dub Dub", 2),
    INTERGALACTIC_WAR("Intergalactic War", 6),
    AQUA_RICK("Aqua Rick", 4),
    LAZY_RICK("Lazy Rick", 5),
    GHOST_IN_A_JAR("Ghost in a Jar", 3),
    SCHWIFTY_LAND("Schwifty Land", 7);

    private final String name;
    private final int gems;
    private final World world;

    Worlds(String name, int gems) {
        this.name = name;
        this.gems = gems;
        this.world = new World(name, gems);
    }

    public String getName() {
        return name;
    }

    public int getGems() {
        return gems;
    }

    public World getWorld() {
        return world;
    }
}
