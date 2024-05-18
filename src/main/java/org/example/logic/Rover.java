package org.example.logic;

import org.example.dataclasses.*;

import java.util.ArrayList;
import java.util.List;

public class Rover extends Vehicle implements Movable{

    public Rover(DirectionalPosition directionalPosition) {
        super(directionalPosition);
    }

    public void move(Instruction[] instructions) {
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.M) {
                this.stepForwards((DirectionalPosition) super.getPosition());
            } else {
                this.rotate((DirectionalPosition) super.getPosition(), instruction);
            }
        }
    }

    public boolean testMovement(Plateau plateau, Instruction[] instructions) {
        Rover proxyRover = new Rover(new DirectionalPosition(this.getX(), this.getY(), this.getDirection()));

        for (Instruction instruction : instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                rotate((DirectionalPosition) proxyRover.getPosition(), instruction);
            } else if (instruction == Instruction.M && !moveForwardIsPossible(proxyRover.getPosition(), plateau)) {
                return false;
            } else {
                stepForwards((DirectionalPosition) proxyRover.getPosition());
            }
        }
        return true;
    }

    Instruction[] truncateMovementSet(Plateau plateau, Instruction[] instructions) {
        List<Instruction> truncatedInstructionsList = new ArrayList<>();
        Rover proxyRover = new Rover(new DirectionalPosition(this.getX(), this.getY(), this.getDirection()));
        for (Instruction instruction: instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                truncatedInstructionsList.add(instruction);
                rotate((DirectionalPosition) proxyRover.getPosition(), instruction);
            } else if (instruction == Instruction.M && moveForwardIsPossible(proxyRover.getPosition(), plateau)) {
                truncatedInstructionsList.add(instruction);
                stepForwards((DirectionalPosition) proxyRover.getPosition());
            } else if (instruction == Instruction.M && !moveForwardIsPossible(proxyRover.getPosition(), plateau)) {
                break;
            }
        }
        Instruction[] truncatedInstructionsArray = new Instruction[truncatedInstructionsList.size()];
        return truncatedInstructionsList.toArray(truncatedInstructionsArray);
    }

    public boolean moveForwardIsPossible(Position position, Plateau plateau) {
        int xInFront = position.getX();
        int yInFront = position.getY();

        if (xInFront < 0 || yInFront < 0 ||
                xInFront > plateau.getPlateauXSize() - 1 ||
                        yInFront > plateau.getPlateauYSize()) {
        return false;
        }
        return plateau.isEmpty(xInFront, yInFront);
    }

    private void rotate(DirectionalPosition currentPosition, Instruction rotationalDirection) {
        if (currentPosition.getDirection() == Direction.N) {
            currentPosition.setDirection(rotationalDirection == Instruction.L ? Direction.W : Direction.E);
        } else if (currentPosition.getDirection() == Direction.E) {
            currentPosition.setDirection(rotationalDirection == Instruction.L ? Direction.N : Direction.S);
        } else if (currentPosition.getDirection() == Direction.S) {
            currentPosition.setDirection(rotationalDirection == Instruction.L ? Direction.E : Direction.W);
        } else if (currentPosition.getDirection() == Direction.W) {
            currentPosition.setDirection(rotationalDirection == Instruction.L ? Direction.S : Direction.N);
        }
    }

    private void stepForwards(DirectionalPosition currentPosition) {
        if (currentPosition.getDirection() == Direction.N) {
            currentPosition.setY(currentPosition.getY() + 1);
        }
        if (currentPosition.getDirection() == Direction.S) {
            currentPosition.setY(currentPosition.getY() - 1);
        }
        if (currentPosition.getDirection() == Direction.E) {
            currentPosition.setX(currentPosition.getX() + 1);
        }
        if (currentPosition.getDirection() == Direction.W) {
            currentPosition.setX(currentPosition.getX() - 1);
        }
    }

    public Direction getDirection() {
        return ((DirectionalPosition)super.getPosition()).getDirection();
    }
}
