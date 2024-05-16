package org.example.logic;

import org.example.dataclasses.*;

public class Rover extends Vehicle implements Movable{

    public Rover(Position position) {
        super(position);
    }

    public void executeMovementInstructions(Instruction[] instructions) {
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.M) {
                super.setPosition(moveForwards(this.reportPosition()));
            } else {
                Direction newDirection = rotate(this.reportPosition().getDirection(), instruction);
                super.setPosition(new Position(this.reportPosition().getX(), this.reportPosition().getY(), newDirection));
            }
        }
    }

    // Although the move() method is contracted due to Rover implementing Movable, there
    // private methods will be used to implements Rover's specific implementation of move()
    private Direction rotate(Direction currentDirection, Instruction rotationalDirection) {
        if (currentDirection == Direction.N) {
            return rotationalDirection == Instruction.L ? Direction.W : Direction.E;
        } else if (currentDirection == Direction.E) {
            return rotationalDirection == Instruction.L ? Direction.N : Direction.S;
        } else if (currentDirection == Direction.S) {
            return rotationalDirection == Instruction.L ? Direction.E : Direction.W;
        } else if (currentDirection == Direction.W) {
            return rotationalDirection == Instruction.L ? Direction.S : Direction.N;
        }
        return null;
    }

    private Position moveForwards(Position currentPosition) {
        int currentX = currentPosition.getX();
        int currentY = currentPosition.getY();
        Direction currentDirection = currentPosition.getDirection();

        if (currentPosition.getDirection() == Direction.W) return new Position(currentX - 1, currentY, currentDirection);
        if (currentPosition.getDirection() == Direction.S) return new Position(currentX, currentY - 1, currentDirection);
        if (currentPosition.getDirection() == Direction.E) return new Position(currentX + 1, currentY, currentDirection);
        if (currentPosition.getDirection() == Direction.N) return new Position(currentX, currentY + 1, currentDirection);
        return null;
    }




}
