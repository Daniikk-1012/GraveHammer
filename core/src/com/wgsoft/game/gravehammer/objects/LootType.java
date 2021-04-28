package com.wgsoft.game.gravehammer.objects;

public enum LootType {
    BRAIN("brain", "Brain", 2, 0),
    EAR("ear", "Ear", 2, 0),
    EYE("eye", "Eye", 2, 0),
    FINGER("finger", "Finger", 2, 0);

    private String name;
    private String description;
    private int cost;
    private int popularity;

    LootType(String name, String description, int cost, int popularity) {
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
