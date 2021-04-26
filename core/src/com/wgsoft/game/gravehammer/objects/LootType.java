package com.wgsoft.game.gravehammer.objects;

public enum LootType {
    BRAIN("brain", "Brain", 1, 1),
    EAR("ear", "Ear", 1, 1),
    EYE("eye", "Eye", 1, 1),
    FINGER("finger", "Finger", 1, 1);

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
