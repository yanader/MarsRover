package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    final private PlateauSize plateauSize;
    final private List<Vehicle> vehicles;

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
        this.vehicles = new ArrayList<>();
    }

    public void landVehicle(Vehicle vehicle) throws PositionOccupiedException {
        if (!isEmpty(vehicle.getX(), vehicle.getY())) {
            throw new PositionOccupiedException("That position is occupied");
        }
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public boolean isEmpty(int x, int y) throws IllegalArgumentException {
        if (x < 0 || y < 0 || x > plateauSize.plateauXSize() || y > plateauSize.plateauYSize()) {
            throw new IllegalArgumentException("Invalid Position");
        }
        for (Vehicle vehicle:vehicles) {
            if (vehicle.getX() == x && vehicle.getY() == y) {
                return false;
            }
        }
        return true;
    }

    public int getPlateauXSize() {
        return this.plateauSize.plateauXSize();
    }

    public int getPlateauYSize() {
        return this.plateauSize.plateauYSize();
    }

    public boolean movementSetIsPossible(Rover rover, Instruction[] instructions) {
        return rover.testMovement(this, instructions);
    }

    public Instruction[] truncateMovementInstructions(Vehicle vehicle, Instruction[] instructions) {
        return ((Rover)vehicle).truncateMovementSet(this, instructions);
    }
}
