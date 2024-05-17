package org.example.dataclasses;

public record Position(int x, int y, Direction direction) {
    public Position {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Position can not be negative");
        }
    }

    @Override
    public String toString() {
        return "Position: (" + x + "," + y + ") Facing: " + direction;
    }
}
