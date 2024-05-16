package org.example.dataclasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void positionCreatesSuccessfully() {
        Position positionOne = new Position(5, 4, Direction.N);
        Position positionTwo = new Position(10, 11, Direction.S);

        assertAll(() -> {
            assertEquals(5, positionOne.getX());
            assertEquals(4, positionOne.getY());
            assertEquals(Direction.N, positionOne.getDirection());
            assertEquals(10, positionTwo.getX());
            assertEquals(11, positionTwo.getY());
            assertEquals(Direction.S, positionTwo.getDirection());
        });
    }

    @Test
    void positionShouldThrowForNegativeXY() {
        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> new Position(-1, 4, Direction.N));
           assertThrows(IllegalArgumentException.class, () -> new Position(5, -7, Direction.N));
           assertThrows(IllegalArgumentException.class, () -> new Position(-8, -9, Direction.N));
        });
    }

    @Test
    void positionZeroZeroDoesNotThrow() {
        assertDoesNotThrow(() -> new Position(0, 0, Direction.N));
    }
}