package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    void roverExtendsVehicleAndImplementsMovable() {
        Vehicle rover = new Rover(new DirectionalPosition(0, 0 , Direction.N));
        assertEquals(Vehicle.class, rover.getClass().getSuperclass());
        assertEquals(Rover.class, rover.getClass());
        assertTrue(Arrays.asList(rover.getClass().getInterfaces()).contains(Movable.class));
    }

    @Test
    void roverReportsPosition() {
        Rover rover = new Rover(new DirectionalPosition(1, 2, Direction.N));

        assertAll(() -> {
           assertEquals(1, rover.getX());
           assertEquals(2, rover.getY());
           assertEquals(Direction.N, rover.getDirection());
        });
    }

    @Test
    void roverMovesCorrectly() {
        Instruction[] moveOne = new Instruction[]{Instruction.M};
        Instruction[] moveTwo = new Instruction[]{Instruction.M, Instruction.M, Instruction.M};
        Instruction[] moveThree = new Instruction[]{Instruction.R, Instruction.M, Instruction.M, Instruction.L};
        Instruction[] moveFour = new Instruction[]{Instruction.R, Instruction.R,Instruction.M, Instruction.M};

        Rover rover = new Rover(new DirectionalPosition(1, 2, Direction.N));

        rover.move(moveOne);
        assertEquals(1, rover.getX());
        assertEquals(3, rover.getY());
        assertEquals(Direction.N, rover.getDirection());

        rover.move(moveTwo);
        assertEquals(1, rover.getPosition().getX());
        assertEquals(6, rover.getPosition().getY());
        assertEquals(Direction.N, rover.getDirection());

        rover.move(moveThree);
        assertEquals(3, rover.getPosition().getX());
        assertEquals(6, rover.getPosition().getY());
        assertEquals(Direction.N, rover.getDirection());

        rover.move(moveFour);
        assertEquals(3, rover.getPosition().getX());
        assertEquals(4, rover.getPosition().getY());
        assertEquals(Direction.S, rover.getDirection());
    }

    @Test
    void roverMoveForwardIsPossibleRespondsCorrectly() throws PositionOccupiedException {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Rover roverOne = new Rover(new DirectionalPosition(0, 0 , Direction.W));
        Rover roverTwo = new Rover(new DirectionalPosition(9, 9 , Direction.N));
        Rover roverThree = new Rover(new DirectionalPosition(9, 3 , Direction.E));

        Rover roverFour = new Rover(new DirectionalPosition(2, 2 , Direction.N));
        Rover roverFive = new Rover(new DirectionalPosition(8, 8 , Direction.S));
        Rover roverSix = new Rover(new DirectionalPosition(4, 5 , Direction.E));

        assertFalse(roverOne.moveForwardIsPossible((DirectionalPosition) roverOne.getPosition(), plateau));
        assertFalse(roverTwo.moveForwardIsPossible((DirectionalPosition)roverTwo.getPosition(), plateau));
        assertFalse(roverThree.moveForwardIsPossible((DirectionalPosition)roverThree.getPosition(), plateau));

        assertTrue(roverFour.moveForwardIsPossible((DirectionalPosition)roverFour.getPosition(), plateau));
        assertTrue(roverFive.moveForwardIsPossible((DirectionalPosition)roverFive.getPosition(), plateau));
        assertTrue(roverSix.moveForwardIsPossible((DirectionalPosition)roverSix.getPosition(), plateau));
    }

    @Test
    void testMovementReturnsCorrectBoolean() throws PositionOccupiedException {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Movable rover = new Rover(new DirectionalPosition(0, 0, Direction.N));

        Instruction[] trueSetOne = {Instruction.M, Instruction.R, Instruction.M, Instruction.L, Instruction.M};
        Instruction[] trueSetTwo = {Instruction.M, Instruction.M, Instruction.M};
        Instruction[] trueSetThree = {Instruction.R, Instruction.R, Instruction.R, Instruction.R, Instruction.M};
        Instruction[] falseSetOne = {Instruction.L, Instruction.M};
        Instruction[] falseSetTwo = {Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M,Instruction.M};
        Instruction[] falseSetThree = {Instruction.R, Instruction.R, Instruction.M};

        assertAll(() -> {
            assertTrue(rover.testMovement(plateau, trueSetOne));
            assertTrue(rover.testMovement(plateau, trueSetTwo));
            assertTrue(rover.testMovement(plateau, trueSetThree));
            assertFalse(rover.testMovement(plateau, falseSetOne));
            assertFalse(rover.testMovement(plateau, falseSetTwo));
            assertFalse(rover.testMovement(plateau, falseSetThree));
        });
    }

    @Test
    void truncateMovementSetReturnsTruncatedSetCorrectly() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Rover rover = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Instruction[] invalidSetOne = {Instruction.L, Instruction.M};
        Instruction[] invalidSetTwo = {Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M,Instruction.M};
        Instruction[] invalidSetThree = {Instruction.R, Instruction.R, Instruction.M};

        Instruction[] outputSetOne = {Instruction.L};
        Instruction[] outputSetTwo = {Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M};
        Instruction[] outputSetThree = {Instruction.R, Instruction.R};

        assertArrayEquals(outputSetOne, rover.truncateMovementSet(plateau, invalidSetOne));
        assertArrayEquals(outputSetTwo, rover.truncateMovementSet(plateau, invalidSetTwo));
        assertArrayEquals(outputSetThree, rover.truncateMovementSet(plateau, invalidSetThree));
    }

    @Test
    void truncateMovementSetReturnsEmptySetCorrectly() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Rover rover = new Rover(new DirectionalPosition(0, 0, Direction.W));
        Instruction[] invalidSetOne = {Instruction.M};
        Instruction[] invalidSetTwo = {};

        Instruction[] emptyArray = {};

        assertArrayEquals(emptyArray, rover.truncateMovementSet(plateau, invalidSetOne));
        assertArrayEquals(emptyArray, rover.truncateMovementSet(plateau, invalidSetTwo));
    }
}