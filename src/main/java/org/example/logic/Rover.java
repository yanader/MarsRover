package org.example.logic;

import org.example.dataclasses.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        return possibleSteps(plateau, instructions) == instructions.length - 1;
    }

    public Instruction[] truncateMovementSet(Plateau plateau, Instruction[] instructions) {
        int validSteps = possibleSteps(plateau, instructions);
        return Arrays.copyOfRange(instructions, 0, validSteps + 1);
    }

    public int possibleSteps(Plateau plateau, Instruction[] instructions) {
        int executedInstructions = -1;
        Rover proxyRover = new Rover(new DirectionalPosition(this.getX(), this.getY(), this.getDirection()));
        for (Instruction instruction: instructions) {
            if (instruction == Instruction.L || instruction == Instruction.R) {
                rotate((DirectionalPosition) proxyRover.getPosition(), instruction);
                executedInstructions++;
            } else if (instruction == Instruction.M && moveForwardIsPossible((DirectionalPosition)proxyRover.getPosition(), plateau)) {
                stepForwards((DirectionalPosition) proxyRover.getPosition());
                executedInstructions++;
            } else if (instruction == Instruction.M && !moveForwardIsPossible((DirectionalPosition)proxyRover.getPosition(), plateau)) {
                return executedInstructions;
            }
        }
        return executedInstructions;
    }

    public boolean moveForwardIsPossible(DirectionalPosition position, Plateau plateau) {
        int xInFront = position.getX();
        int yInFront = position.getY();

        if (position.getDirection() == Direction.N) yInFront++;
        if (position.getDirection() == Direction.S) yInFront--;
        if (position.getDirection() == Direction.E) xInFront++;
        if (position.getDirection() == Direction.W) xInFront--;

        if (xInFront < 0 || yInFront < 0 ||
                xInFront > plateau.getPlateauXSize() - 1 ||
                        yInFront > plateau.getPlateauYSize() - 1) {
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
