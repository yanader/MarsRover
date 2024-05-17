package org.example.logic;

import org.example.dataclasses.Position;

public abstract class Vehicle {
    private Position position;

    public Vehicle(Position position) {
        this.position = position;
    }

    public Position reportPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String shortPosition() {
        return "Position: x -> " + this.reportPosition().x() + " | y -> "
                + this.reportPosition().y() + " | direction -> "
                + this.reportPosition().direction().toString();
    }

}
