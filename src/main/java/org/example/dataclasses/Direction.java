package org.example.dataclasses;

public enum Direction {
    N,
    E,
    S,
    W,
    ;

    @Override
    public String toString() {
        return switch (this) {
            case N -> "North";
            case E -> "East";
            case S -> "South";
            case W -> "West";
            default -> null;
        };
    }
}
