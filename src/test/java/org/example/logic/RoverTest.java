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
           assertEquals(1, rover.getPosition().x());
           assertEquals(2, rover.getPosition().y());
           assertEquals(Direction.N, rover.getPosition().direction());
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
        assertEquals(1, rover.getPosition().x());
        assertEquals(3, rover.getPosition().y());
        assertEquals(Direction.N, rover.getPosition().direction());

        rover.move(moveTwo);
        assertEquals(1, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.N, rover.getPosition().direction());

        rover.move(moveThree);
        assertEquals(3, rover.getPosition().x());
        assertEquals(6, rover.getPosition().y());
        assertEquals(Direction.N, rover.getPosition().direction());

        rover.move(moveFour);
        assertEquals(3, rover.getPosition().x());
        assertEquals(4, rover.getPosition().y());
        assertEquals(Direction.S, rover.getPosition().direction());

    }
}