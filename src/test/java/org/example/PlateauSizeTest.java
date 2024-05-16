package org.example;

import org.example.dataclasses.PlateauSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauSizeTest {

    @Test
    void plateauSizeShouldCreateObjectCorrect() {
        PlateauSize plateauOne = new PlateauSize(1, 1);
        PlateauSize plateauTwo = new PlateauSize(7, 14);

        assertEquals(1, plateauOne.getPlateauXSize());
        assertEquals(1, plateauOne.getPlateauYSize());
        assertEquals(7, plateauTwo.getPlateauXSize());
        assertEquals(14, plateauTwo.getPlateauYSize());

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