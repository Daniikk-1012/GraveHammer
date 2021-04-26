package com.wgsoft.game.gravehammer.objects;

public enum PotionType {
    BLUE("blue", "Van Gogh", 25, 20),
    CYAN("cyan", "Boner", 15, 12),
    GREEN("green", "Big baby head", 10, 5),
    PINK("pink", "Eardrink", 3, 10),
    RED("red", "Tickled ear", 5, 7),
    YELLOW("yellow", "Pull a finger", 20, 13);

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
