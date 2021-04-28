package com.wgsoft.game.gravehammer.objects;

import java.util.Arrays;

import com.badlogic.gdx.utils.ObjectMap;

public class PotionBrew {
    private static final ObjectMap<LootType[], PotionType> recipeMap =
        new ObjectMap<>();

    static {
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.EYE,
                LootType.EAR,
        }, PotionType.VAN_GOGH);
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.EYE,
                LootType.EYE,
        }, PotionType.BONER);
        recipeMap.put(new LootType[] {
                LootType.BRAIN,
                LootType.BRAIN,
                LootType.BRAIN,
        }, PotionType.BIG_BABY_HEAD);
        recipeMap.put(new LootType[] {
                LootType.EAR,
                LootType.EAR,
                LootType.BRAIN,
        }, PotionType.EARDRINK);
        recipeMap.put(new LootType[] {
                LootType.FINGER,
                LootType.EAR,
                LootType.FINGER,
        }, PotionType.TICKLED_EAR);
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
