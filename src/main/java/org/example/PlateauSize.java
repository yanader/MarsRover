package org.example;

public class PlateauSize {
    private final int plateauXSize;
    private final int plateauYSize;

    public PlateauSize(int plateauXSize, int plateauYSize) {
        if (plateauXSize < 0 || plateauYSize < 0) {
            throw new IllegalArgumentException("Plateau dimensions can not be negative");
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
