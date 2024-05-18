package org.example.logic;

import org.example.dataclasses.*;

public class Rover extends Vehicle implements Movable{
//    private DirectionalPosition directionalPosition;

    public Rover(DirectionalPosition directionalPosition) {
        super(directionalPosition);
//        this.directionalPosition = directionalPosition;
    }

    public void move(Instruction[] instructions) {
        for (Instruction instruction : instructions) {
            if (instruction == Instruction.M) {
                this.moveForwards((DirectionalPosition) super.getPosition());
            } else {
                this.rotate((DirectionalPosition) super.getPosition(), instruction);
            }
        }
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

    private void moveForwards(DirectionalPosition currentPosition) {
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




}
