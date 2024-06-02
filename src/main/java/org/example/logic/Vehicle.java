package org.example.logic;

import org.example.dataclasses.Position;

public abstract class Vehicle {
    private Position position;
    private int id;

    public Vehicle(Position position) {
        this.position = position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String outputPosition() {
        return this.position.toString();
    }


}
