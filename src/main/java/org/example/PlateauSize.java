package org.example;

public class PlateauSize {
    private final int plateauDepth;
    private final int getPlateauWidth;

    public PlateauSize(int plateauDepth, int getPlateauWidth) {
        this.plateauDepth = plateauDepth;
        this.getPlateauWidth = getPlateauWidth;
    }

    public int getPlateauDepth() {
        return plateauDepth;
    }

    public int getGetPlateauWidth() {
        return getPlateauWidth;
    }
}
