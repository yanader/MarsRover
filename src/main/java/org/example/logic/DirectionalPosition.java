package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Position;

public class DirectionalPosition extends Position {
    private Direction direction;

    public DirectionalPosition(int x, int y, Direction direction) {
        super(x, y);
        this.direction = direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String toString(){
        return super.toString() + " | direction -> " + direction;
    }
}
