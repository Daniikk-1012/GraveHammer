package com.wgsoft.game.gravehammer.objects;

import java.util.Arrays;

import com.badlogic.gdx.utils.ObjectMap;

public class PotionBrew {
    private static final ObjectMap<LootType[], PotionType> recipeMap =
        new ObjectMap<>();

    static {
        // BRAIN EYE EAR = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.EYE,
                LootType.EAR,
        }, PotionType.VAN_GOGH);
        // BRAIN EAR EYE = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.EAR,
                LootType.EYE,
        }, PotionType.VAN_GOGH);
        // EYE BRAIN EAR = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.EYE,
                LootType.BRAIN,
                LootType.EAR,
        }, PotionType.VAN_GOGH);
        // EYE EAR BRAIN = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.EYE,
                LootType.EAR,
                LootType.BRAIN,
        }, PotionType.VAN_GOGH);
        // EAR BRAIN EYE = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.BRAIN,
                LootType.EYE,
        }, PotionType.VAN_GOGH);
        // EAR EYE BRAIN = VAN_GOGH
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.EYE,
                LootType.BRAIN,
        }, PotionType.VAN_GOGH);
        // FINGER EYE EYE = BONER
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.EYE,
                LootType.EYE,
        }, PotionType.BONER);
        // EYE FINGER EYE = BONER
        recipeMap.put(new LootType[] {
                LootType.EYE,
                LootType.FINGER,
                LootType.EYE,
        }, PotionType.BONER);
        // EYE EYE FINGER = BONER
        recipeMap.put(new LootType[] {
                LootType.EYE,
                LootType.EYE,
                LootType.FINGER,
        }, PotionType.BONER);
        // BRAIN BRAIN BRAIN = BIG_BABY_HEAD
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.BRAIN,
                LootType.BRAIN,
        }, PotionType.BIG_BABY_HEAD);
        // EAR EAR BRAIN = EARDRINK
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.EAR,
                LootType.BRAIN,
        }, PotionType.EARDRINK);
        // EAR BRAIN EAR = EARDRINK
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.BRAIN,
                LootType.EAR,
        }, PotionType.EARDRINK);
        // BRAIN EAR EAR = EARDRINK
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.EAR,
                LootType.EAR,
        }, PotionType.EARDRINK);
        // FINGER FINGER EAR = TICKLED_EAR
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.FINGER,
                LootType.EAR,
        }, PotionType.TICKLED_EAR);
        // FINGER EAR FINGER = TICKLED_EAR
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.EAR,
                LootType.FINGER,
        }, PotionType.TICKLED_EAR);
        // EAR FINGER FINGER = TICKLED_EAR
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.FINGER,
                LootType.FINGER,
        }, PotionType.TICKLED_EAR);
        // FINGER FINGER FINGER = PULL_A_FINGER
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.FINGER,
                LootType.FINGER,
        }, PotionType.PULL_A_FINGER);
    }

    public static PotionType brew(LootType[] input) {
        final ObjectMap.Entries<LootType[], PotionType> entries
            = new ObjectMap.Entries<>(recipeMap);
        for(ObjectMap.Entry<LootType[], PotionType> entry: entries) {
            if(Arrays.equals(input, entry.key)) {
                return entry.value;
            }
        }
        return null;
    }
}
