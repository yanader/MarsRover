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
    void plateauIsEmptyRespondsCorrectly() throws PositionOccupiedException {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle roverOne = new Rover(new DirectionalPosition(1, 1 , Direction.N));
        Vehicle roverTwo = new Rover(new DirectionalPosition(2, 2 , Direction.N));
        Vehicle roverThree = new Rover(new DirectionalPosition(3, 3 , Direction.N));

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
    void plateauAcceptsVehiclesOfMultipleSubTypes() {
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        Vehicle rover = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Vehicle miner = new Miner(new DirectionalPosition(1, 1, Direction.N));

        assertAll(() -> {
            assertDoesNotThrow(() -> plateau.landVehicle(rover));
            assertDoesNotThrow(() -> plateau.landVehicle(miner));
        });
    }

    @Test
    void plateauThrowsExceptionWhenLandingOnOccupiedPosition() throws PositionOccupiedException {
        Vehicle vehicleOne = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Vehicle vehicleTwo = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        plateau.landVehicle(vehicleOne);

        assertThrows(PositionOccupiedException.class, () -> plateau.landVehicle(vehicleTwo));
    }

    @Test
    void plateauCorrectlyReturnsVehicles() throws PositionOccupiedException {
        Vehicle vehicleOne = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Vehicle vehicleTwo = new Rover(new DirectionalPosition(0, 1, Direction.N));
        Vehicle vehicleThree = new Miner(new Position(0, 2));
        Plateau plateau = new Plateau(new PlateauSize(10, 10));
        plateau.landVehicle(vehicleOne);
        plateau.landVehicle(vehicleTwo);
        plateau.landVehicle(vehicleThree);

        assertEquals(3, plateau.getVehicles().size());
    }
}