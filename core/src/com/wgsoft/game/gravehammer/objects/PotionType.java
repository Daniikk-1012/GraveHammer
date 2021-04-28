package com.wgsoft.game.gravehammer.objects;

public enum PotionType {
    VAN_GOGH("van-gogh", "Van Gogh", 25, 20),
    BONER("boner", "Boner", 15, 12),
    BIG_BABY_HEAD("big-baby-head", "Big baby head", 10, 5),
    EARDRINK("eardrink", "Eardrink", 3, 10),
    TICKLED_EAR("tickled-ear", "Tickled ear", 5, 7),
    PULL_A_FINGER("pull-a-finger", "Pull a finger", 20, 13);

    private String name;
    private String description;
    private int cost;
    private int popularity;

    PotionType(String name, String description, int cost, int popularity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }
    
    public int getPopularity() {
        return popularity;
    }
}
