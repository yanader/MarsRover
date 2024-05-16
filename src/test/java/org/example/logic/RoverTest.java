package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void roverExtendsVehicleAndImplementsMovable() {
        Vehicle rover = new Rover(new Position(0, 0 , Direction.N));
        assertEquals(Vehicle.class, rover.getClass().getSuperclass());
        assertEquals(Rover.class, rover.getClass());
        assertEquals(Movable.class, rover.getClass().getInterfaces()[0]);
    }

    @Test
    void roverReportsPosition() {
        Vehicle rover = new Rover(new Position(1, 2, Direction.N));

        assertAll(() -> {
           assertEquals(1, rover.reportPosition().getX());
           assertEquals(2, rover.reportPosition().getY());
           assertEquals(Direction.N, rover.reportPosition().getDirection());
        });
    }



    @Test
    void roverMovesCorrectly() {
        Instruction[] moveOne = new Instruction[]{Instruction.M};
        Instruction[] moveTwo = new Instruction[]{Instruction.M, Instruction.M, Instruction.M};
        Instruction[] moveThree = new Instruction[]{Instruction.R, Instruction.M, Instruction.M, Instruction.L};
        Instruction[] moveFour = new Instruction[]{Instruction.R, Instruction.R,Instruction.M, Instruction.M};

        Rover rover = new Rover(new Position(1, 2, Direction.N));

        rover.move(moveOne);
        assertEquals(1, rover.reportPosition().getX());
        assertEquals(3, rover.reportPosition().getY());
        assertEquals(Direction.N, rover.reportPosition().getDirection());

        rover.move(moveTwo);
        assertEquals(1, rover.reportPosition().getX());
        assertEquals(6, rover.reportPosition().getY());
        assertEquals(Direction.N, rover.reportPosition().getDirection());

        rover.move(moveThree);
        assertEquals(3, rover.reportPosition().getX());
        assertEquals(6, rover.reportPosition().getY());
        assertEquals(Direction.N, rover.reportPosition().getDirection());

        rover.move(moveFour);
        assertEquals(3, rover.reportPosition().getX());
        assertEquals(4, rover.reportPosition().getY());
        assertEquals(Direction.S, rover.reportPosition().getDirection());





    }
}