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
        if (!isEmpty(vehicle.getPosition().x(), vehicle.getPosition().y())) {
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
            if (vehicle.getPosition().x() == x && vehicle.getPosition().y() == y) {
                return false;
            }
        }
        return true;
    }

    public boolean movementSetIsPossible(Vehicle vehicle, Instruction[] instructions) {
        Rover proxyRover = new Rover(new Position(vehicle.getPosition().x(), vehicle.getPosition().y(), vehicle.getPosition().direction()));
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                Direction newDirection = proxyRover.rotate(proxyRover.getPosition().direction(), instruction);
                proxyRover.setPosition(new Position(proxyRover.getPosition().x(), proxyRover.getPosition().y(), newDirection));
            } else if (instruction == Instruction.M && !moveForwardIsPossible(proxyRover)) {
                return false;
            } else {
                Position newPosition = proxyRover.moveForwards(proxyRover.getPosition());
                proxyRover.setPosition(newPosition);
            }
        }
        return true;
    }

    public Instruction[] truncateMovementInstructions(Vehicle vehicle, Instruction[] instructions) {
        List<Instruction> truncatedInstructionSet = new ArrayList<>();
        Rover proxyRover = new Rover(new Position(vehicle.getPosition().x(), vehicle.getPosition().y(), vehicle.getPosition().direction()));
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                truncatedInstructionSet.add(instruction);
                Direction newDirection = proxyRover.rotate(proxyRover.getPosition().direction(), instruction);
                proxyRover.setPosition(new Position(proxyRover.getPosition().x(), proxyRover.getPosition().y(), newDirection));
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
        if (vehicle.getPosition().direction() == Direction.W) {
            return vehicle.getPosition().x() - 1;
        } else if (vehicle.getPosition().direction() == Direction.E) {
            return vehicle.getPosition().x() + 1;
        } else return vehicle.getPosition().x();
    }

    private int yCoordinateInFrontOfVehicle(Vehicle vehicle) {
        if (vehicle.getPosition().direction() == Direction.S) {
            return vehicle.getPosition().y() - 1;
        } else if (vehicle.getPosition().direction() == Direction.N) {
            return vehicle.getPosition().y() + 1;
        } else {
            return vehicle.getPosition().y();
        }
    }
}
