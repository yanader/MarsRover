package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    final private PlateauSize plateauSize;
    private List<Vehicle> vehicles;

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
        this.vehicles = new ArrayList<>();
    }

    public boolean isEmpty(int x, int y) {
        return false;
    }

    public boolean moveForwardIsPossible(Vehicle vehicle) {
        return false;
    }
}
