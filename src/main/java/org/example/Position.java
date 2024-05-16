package org.example;

public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Position can not be negative");
        }
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Position: (" + x + "," + y + ") Facing: " + direction;
    }
}
