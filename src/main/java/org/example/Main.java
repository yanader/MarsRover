package org.example;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.logic.Rover;


public class Main {
    public static void main(String[] args) {
        Rover rover = new Rover(new Position(0, 0, Direction.S));

        System.out.println(rover.rotate(Direction.N, Instruction.R));

    }
}