package org.example.logic;

import org.example.dataclasses.Position;

public abstract class Vehicle {
    private Position position;

    public Vehicle(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String outputPosition() {
        return this.position.toString();
    }

}
