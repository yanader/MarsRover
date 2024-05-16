package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;

public abstract class Vehicle {
    Position position;

    public Vehicle(Position position) {
        this.position = position;
    }

    public Position reportPosition() {
        return this.position;
    }

}
