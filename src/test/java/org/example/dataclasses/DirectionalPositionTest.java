package org.example.dataclasses;

import org.example.logic.DirectionalPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionalPositionTest {

    @Test
    void directionalPositionCreatesSuccessfully() {
        DirectionalPosition positionOne = new DirectionalPosition(5, 4, Direction.N);
        DirectionalPosition positionTwo = new DirectionalPosition(10, 11, Direction.S);

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
    void directionalPositionShouldThrowForNegativeXY() {
        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> new DirectionalPosition(-1, 4, Direction.N));
            assertThrows(IllegalArgumentException.class, () -> new DirectionalPosition(5, -7, Direction.N));
            assertThrows(IllegalArgumentException.class, () -> new DirectionalPosition(-8, -9, Direction.N));
        });
    }

    @Test
    void directionalPositionZeroZeroDoesNotThrow() {
        assertDoesNotThrow(() -> new DirectionalPosition(0, 0, Direction.N));
    }
}