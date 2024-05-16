package org.example.dataclasses;

public class PlateauSize {
    private final int plateauXSize;
    private final int plateauYSize;

    public PlateauSize(int plateauXSize, int plateauYSize) {
        if (plateauXSize < 1 || plateauYSize < 1) {
            throw new IllegalArgumentException("Plateau dimensions can not be zero/negative");
        }
        this.plateauXSize = plateauXSize;
        this.plateauYSize = plateauYSize;
    }

    public int getPlateauXSize() {
        return plateauXSize;
    }

    public int getPlateauYSize() {
        return plateauYSize;
    }
}
