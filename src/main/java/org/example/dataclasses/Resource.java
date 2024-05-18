package org.example.dataclasses;

import java.util.List;
import java.util.Random;

public enum Resource {
    SALT(10, "Salt"),
    GOLD(3, "Gold"),
    SILVER(7, "Silver"),
    IRON(10, "Iron"),
    ROCK(70, "Rock");

    private final int weight;
    private final String name;

    Resource(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    private static final List<Resource> RESOURCES =
            List.of(values());

    private static int totalWeighting() {
        int totalWeighting = 0;
        for (Resource resource : RESOURCES) {
            totalWeighting += resource.getWeight();
        }
        return totalWeighting;
    }

    private static final Random RANDOM = new Random();
    private static final int TOTAL_WEIGHT = totalWeighting();

    public static Resource digForResource() {
        int randomInt = RANDOM.nextInt(TOTAL_WEIGHT);
        int weightTally = 0;
        for (Resource r : RESOURCES) {
            weightTally += r.getWeight();
            if (randomInt < weightTally) {
                return r;
            }
        }
        return null;
    }
}