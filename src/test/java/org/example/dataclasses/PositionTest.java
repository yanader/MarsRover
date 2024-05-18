package org.example.dataclasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void positionCreatesSuccessfully() {
        Position positionOne = new Position(5, 4);
        Position positionTwo = new Position(10, 11);

        assertAll(() -> {
            assertEquals(5, positionOne.getX());
            assertEquals(4, positionOne.getY());
            assertEquals(10, positionTwo.getX());
            assertEquals(11, positionTwo.getY());
        });
    }

    @Test
    void positionShouldThrowForNegativeXY() {
        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> new Position(-1, 4));
           assertThrows(IllegalArgumentException.class, () -> new Position(5, -7));
           assertThrows(IllegalArgumentException.class, () -> new Position(-8, -9));
        });
    }

    @Test
    void positionZeroZeroDoesNotThrow() {
        assertDoesNotThrow(() -> new Position(0, 0));
    }
}