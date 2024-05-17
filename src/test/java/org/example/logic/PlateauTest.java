package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {

    @Test
    void plateauIsEmptyRejectsValidPositions() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));

        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> plateau.isEmpty(0, -1));
           assertThrows(IllegalArgumentException.class, () -> plateau.isEmpty(-1, 5));
           assertThrows(IllegalArgumentException.class, () -> plateau.isEmpty(4, 15));
           assertThrows(IllegalArgumentException.class, () -> plateau.isEmpty(16, 7));
        });
    }

    @Test
    void plateauIsEmptyRespondsCorrectly() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle roverOne = new Rover(new Position(1, 1 , Direction.N));
        Vehicle roverTwo = new Rover(new Position(2, 2 , Direction.N));
        Vehicle roverThree = new Rover(new Position(3, 3 , Direction.N));

        plateau.landVehicle(roverOne);
        plateau.landVehicle(roverTwo);
        plateau.landVehicle(roverThree);

        assertAll(() -> {
            assertTrue(plateau.isEmpty(0, 0));
            assertTrue(plateau.isEmpty(3, 4));
            assertTrue(plateau.isEmpty(10, 10));

            assertFalse(plateau.isEmpty(1, 1));
            assertFalse(plateau.isEmpty(2, 2));
            assertFalse(plateau.isEmpty(3, 3));
        });
    }

    @Test
    void plateauMoveForwardIsPossibleRespondsCorrectly() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle roverOne = new Rover(new Position(0, 0 , Direction.W));
        Vehicle roverTwo = new Rover(new Position(9, 9 , Direction.N));
        Vehicle roverThree = new Rover(new Position(13, 0 , Direction.E));
        plateau.landVehicle(roverOne);
        plateau.landVehicle(roverTwo);
        plateau.landVehicle(roverThree);

        Vehicle roverFour = new Rover(new Position(2, 2 , Direction.N));
        Vehicle roverFive = new Rover(new Position(9, 9 , Direction.S));
        Vehicle roverSix = new Rover(new Position(4, 5 , Direction.E));
        plateau.landVehicle(roverFour);
        plateau.landVehicle(roverFive);
        plateau.landVehicle(roverSix);

        assertAll(() -> {
            assertFalse(plateau.moveForwardIsPossible(roverOne));
            assertFalse(plateau.moveForwardIsPossible(roverTwo));
            assertFalse(plateau.moveForwardIsPossible(roverThree));

            assertTrue(plateau.moveForwardIsPossible(roverFour));
            assertTrue(plateau.moveForwardIsPossible(roverFive));
            assertTrue(plateau.moveForwardIsPossible(roverSix));
        });
    }

    @Test
    void movementSetIsPossibleReturnsCorrectBoolean() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle rover = new Rover(new Position(0, 0, Direction.N));
        plateau.landVehicle(rover);
        Instruction[] trueSetOne = {Instruction.M, Instruction.R, Instruction.M, Instruction.L, Instruction.M};
        Instruction[] trueSetTwo = {Instruction.M, Instruction.M, Instruction.M};
        Instruction[] trueSetThree = {Instruction.R, Instruction.R, Instruction.R, Instruction.R, Instruction.M};
        Instruction[] falseSetOne = {Instruction.L, Instruction.M};
        Instruction[] falseSetTwo = {Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M, Instruction.M,Instruction.M};
        Instruction[] falseSetThree = {Instruction.R, Instruction.R, Instruction.M};


        assertAll(() -> {
           assertTrue(plateau.movementSetIsPossible(rover, trueSetOne));
           assertTrue(plateau.movementSetIsPossible(rover, trueSetTwo));
           assertTrue(plateau.movementSetIsPossible(rover, trueSetThree));
           assertFalse(plateau.movementSetIsPossible(rover, falseSetOne));
           assertFalse(plateau.movementSetIsPossible(rover, falseSetTwo));
           assertFalse(plateau.movementSetIsPossible(rover, falseSetThree));

        });
    }

    @Test
    void plateauAcceptsVehiclesOfMultipleSubTypes() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle rover = new Rover(new Position(0, 0, Direction.N));
        Vehicle miner = new Miner(new Position(1, 1, Direction.N));

        assertAll(() -> {
            assertDoesNotThrow(() -> plateau.landVehicle(rover));
            assertDoesNotThrow(() -> plateau.landVehicle(miner));
        });
    }
}