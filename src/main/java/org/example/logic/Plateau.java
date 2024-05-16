package org.example.logic;

import org.example.dataclasses.Direction;
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

    public void landVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public boolean isEmpty(int x, int y) throws IllegalArgumentException {
        if (x < 0 || y < 0 || x > plateauSize.getPlateauXSize() || y > plateauSize.getPlateauYSize()) {
            throw new IllegalArgumentException("Invalid Position");
        }
        for (Vehicle vehicle:vehicles) {
            if (vehicle.reportPosition().getX() == x && vehicle.reportPosition().getY() == y) {
                return false;
            }
        }
        return true;
    }

    public boolean moveForwardIsPossible(Vehicle vehicle) {
        int xInFront = xCoordinateInFrontOfVehicle(vehicle);
        int yInFront = yCoordinateInFrontOfVehicle(vehicle);

        if (xInFront < 0 || yInFront < 0 ||
                xInFront > plateauSize.getPlateauXSize() - 1 ||
        yInFront > plateauSize.getPlateauYSize() - 1) {
            return false;
        }
        return isEmpty(xInFront, yInFront);
    }

    private int xCoordinateInFrontOfVehicle(Vehicle vehicle) {
        if (vehicle.reportPosition().getDirection() == Direction.W) {
            return vehicle.reportPosition().getX() - 1;
        } else if (vehicle.reportPosition().getDirection() == Direction.E) {
            return vehicle.reportPosition().getX() + 1;
        } else return vehicle.reportPosition().getX();
    }

    private int yCoordinateInFrontOfVehicle(Vehicle vehicle) {
        if (vehicle.reportPosition().getDirection() == Direction.S) {
            return vehicle.reportPosition().getY() - 1;
        } else if (vehicle.reportPosition().getDirection() == Direction.N) {
            return vehicle.reportPosition().getY() + 1;
        } else {
            return vehicle.reportPosition().getY();
        }
    }
}
