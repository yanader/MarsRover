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
        List<Instruction> truncatedInstructionSet = new ArrayList<>();
        Rover proxyRover = new Rover(new Position(vehicle.getPosition().getX(), vehicle.getPosition().getY(), vehicle.getPosition().getDirection()));
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                truncatedInstructionSet.add(instruction);
                Direction newDirection = proxyRover.rotate(proxyRover.getPosition().getDirection(), instruction);
                proxyRover.setPosition(new Position(proxyRover.getPosition().getX(), proxyRover.getPosition().getY(), newDirection));
            } else if (instruction == Instruction.M && moveForwardIsPossible(proxyRover)) {
                truncatedInstructionSet.add(instruction);
                Position newPosition = proxyRover.moveForwards(proxyRover.getPosition());
                proxyRover.setPosition(newPosition);
            } else if (instruction == Instruction.M && !moveForwardIsPossible(proxyRover)) {
                break;
            }
        }
        Instruction[] truncatedInstructionArray = new Instruction[truncatedInstructionSet.size()];
        return truncatedInstructionSet.toArray(truncatedInstructionArray);
    }

    public boolean moveForwardIsPossible(Vehicle vehicle) {
        int xInFront = xCoordinateInFrontOfVehicle(vehicle);
        int yInFront = yCoordinateInFrontOfVehicle(vehicle);

        if (xInFront < 0 || yInFront < 0 ||
                xInFront > plateauSize.plateauXSize() - 1 ||
        yInFront > plateauSize.plateauYSize() - 1) {
            return false;
        }
        return isEmpty(xInFront, yInFront);
    }

    private int xCoordinateInFrontOfVehicle(Vehicle vehicle) {
        if (vehicle.getPosition().getDirection() == Direction.W) {
            return vehicle.getPosition().getX() - 1;
        } else if (vehicle.getPosition().getDirection() == Direction.E) {
            return vehicle.getPosition().getX() + 1;
        } else return vehicle.getPosition().getX();
    }

    private int yCoordinateInFrontOfVehicle(Vehicle vehicle) {
        if (vehicle.getPosition().getDirection() == Direction.S) {
            return vehicle.getPosition().getY() - 1;
        } else if (vehicle.getPosition().getDirection() == Direction.N) {
            return vehicle.getPosition().getY() + 1;
        } else {
            return vehicle.getPosition().getY();
        }
    }
}
