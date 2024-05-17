package org.example.dataclasses;

public enum Resource {
    SALT(10, "Sale"),
    GOLD(3, "Gold"),
    SILVER(7, "Silver"),
    IRON(10, "Iron"),
    ROCK(70, "Rock");

    private int weight;

    Resource(int i, String name) {

    }

    public Resource dig() {
        return Resource.GOLD;
    }
}
