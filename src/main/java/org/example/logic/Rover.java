package org.example.logic;

import org.example.dataclasses.*;

public class Rover extends Vehicle implements Movable{

    public Rover(Position position) {
        super(position);
    }

    public void move(Instruction[] instructions) {
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.M) {
                super.setPosition(moveForwards(this.reportPosition()));
            } else {
                Direction newDirection = rotate(this.reportPosition().direction(), instruction);
                super.setPosition(new Position(this.reportPosition().x(), this.reportPosition().y(), newDirection));
            }
        }
    }

    // Although the move() method is contracted due to Rover implementing Movable, there
    // private methods will be used to implements Rover's specific implementation of move()
    protected Direction rotate(Direction currentDirection, Instruction rotationalDirection) {
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

    protected Position moveForwards(Position currentPosition) {
        int currentX = currentPosition.x();
        int currentY = currentPosition.y();
        Direction currentDirection = currentPosition.direction();

        if (currentPosition.direction() == Direction.W) return new Position(currentX - 1, currentY, currentDirection);
        if (currentPosition.direction() == Direction.S) return new Position(currentX, currentY - 1, currentDirection);
        if (currentPosition.direction() == Direction.E) return new Position(currentX + 1, currentY, currentDirection);
        if (currentPosition.direction() == Direction.N) return new Position(currentX, currentY + 1, currentDirection);
        return null;
    }




}
