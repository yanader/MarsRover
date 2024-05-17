package org.example.dataclasses;

public record PlateauSize(int plateauXSize, int plateauYSize) {
    public PlateauSize {
        if (plateauXSize < 1 || plateauYSize < 1) {
            throw new IllegalArgumentException("Plateau dimensions can not be zero/negative");
        }
    }
}
