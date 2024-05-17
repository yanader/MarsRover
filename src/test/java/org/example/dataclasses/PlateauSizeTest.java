package org.example.dataclasses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauSizeTest {

    @Test
    void plateauSizeShouldCreateObjectCorrect() {
        PlateauSize plateauOne = new PlateauSize(1, 1);
        PlateauSize plateauTwo = new PlateauSize(7, 14);

        assertEquals(1, plateauOne.plateauXSize());
        assertEquals(1, plateauOne.plateauYSize());
        assertEquals(7, plateauTwo.plateauXSize());
        assertEquals(14, plateauTwo.plateauYSize());

    }

    @Test
    void plateauSizeShouldThrowExceptionForInvalidInputs() {
        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> new PlateauSize(-1, 2));
            assertThrows(IllegalArgumentException.class, () -> new PlateauSize(0, -2));
            assertThrows(IllegalArgumentException.class, () -> new PlateauSize(-5, -3));
            assertThrows(IllegalArgumentException.class, () -> new PlateauSize(0, 0));
            assertThrows(IllegalArgumentException.class, () -> new PlateauSize(5, 0));
        });




    }
}