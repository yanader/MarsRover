package org.example.parsers;

import org.example.dataclasses.Direction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.example.logic.DirectionalPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetupInputParserTest {

    // Testing for initial position input parsing

    @Test
    void createPlateauSizeReturnsPlateauSize() {
        assertEquals(PlateauSize.class, SetupInputParser.createPlateauSize("5 5").getClass());
    }

    @Test
    void createPlateauSizeRejectsInvalidInput() {
        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPlateauSize("e"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPlateauSize("44"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPlateauSize("5 5 N"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPlateauSize("5 0"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPlateauSize("-5 5 "));
        });
    }

    @Test
    void createPlateauSizeAcceptsValidInputs() {
        assertAll(() -> {
            assertDoesNotThrow(() -> SetupInputParser.createPlateauSize("1 1"));
            assertDoesNotThrow(() -> SetupInputParser.createPlateauSize("10 5"));
            assertDoesNotThrow(() -> SetupInputParser.createPlateauSize("15 300"));
        });
    }


    @Test
    void createPlateauSizeReturnsCorrectPlateauSize() {
        PlateauSize plateauSizeOne = SetupInputParser.createPlateauSize("10 10");
        PlateauSize plateauSizeTwo = SetupInputParser.createPlateauSize("1 100");
        PlateauSize plateauSizeThree = SetupInputParser.createPlateauSize("15 16");

        assertAll(() -> {
                    assertEquals(10, plateauSizeOne.plateauXSize());
                    assertEquals(10, plateauSizeOne.plateauYSize());
                    assertEquals(1, plateauSizeTwo.plateauXSize());
                    assertEquals(100, plateauSizeTwo.plateauYSize());
                    assertEquals(15, plateauSizeThree.plateauXSize());
                    assertEquals(16, plateauSizeThree.plateauYSize());
                });
    }

    // Testing for DirectionalPosition input parsing

    @Test
    void createDirectionalPositionReturnsPosition() {
        assertEquals(DirectionalPosition.class, SetupInputParser.createDirectionalPosition("5 5 N").getClass());
    }

    @Test
    void createDirectionalPositionRejectsInvalidInput() {
        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createDirectionalPosition("1 1"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createDirectionalPosition("1 1 F"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createDirectionalPosition("11N"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createDirectionalPosition("-1 1 N"));
        });
    }

    @Test
    void createDirectionalPositionAcceptsValidInputs() {
        assertAll(() -> {
            assertDoesNotThrow(() -> SetupInputParser.createDirectionalPosition("1 1 N"));
            assertDoesNotThrow(() -> SetupInputParser.createDirectionalPosition("0 0 E"));
            assertDoesNotThrow(() -> SetupInputParser.createDirectionalPosition("100 17 W"));
            assertDoesNotThrow(() -> SetupInputParser.createDirectionalPosition("0 50 S"));
        });
    }

    @Test
    void createDirectionalPositionReturnsCorrectPosition() {
            DirectionalPosition positionOne = SetupInputParser.createDirectionalPosition("0 0 N");
            DirectionalPosition positionTwo = SetupInputParser.createDirectionalPosition("15 14 S");
            DirectionalPosition positionThree = SetupInputParser.createDirectionalPosition("13 100 W");

            assertAll(() -> {
                assertEquals(0,positionOne.getX());
                assertEquals(0,positionOne.getY());
                Assertions.assertEquals(Direction.N,positionOne.getDirection());
                assertEquals(15,positionTwo.getX());
                assertEquals(14,positionTwo.getY());
                assertEquals(Direction.S,positionTwo.getDirection());
                assertEquals(13,positionThree.getX());
                assertEquals(100,positionThree.getY());
                assertEquals(Direction.W,positionThree.getDirection());
            });
    }

    // Testing for Position input parsing

    @Test
    void createPositionReturnsPosition() {
        assertEquals(Position.class, SetupInputParser.createPosition("5 5").getClass());
    }

    @Test
    void createPositionRejectsInvalidInput() {
        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPosition("1 1 S"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPosition("1 1 F"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPosition("11"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPosition("-1 1"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createPosition("1 -1"));
        });
    }

    @Test
    void createPositionAcceptsValidInputs() {
        assertAll(() -> {
            assertDoesNotThrow(() -> SetupInputParser.createPosition("1 1"));
            assertDoesNotThrow(() -> SetupInputParser.createPosition("0 0"));
            assertDoesNotThrow(() -> SetupInputParser.createPosition("100 17"));
            assertDoesNotThrow(() -> SetupInputParser.createPosition("0 50"));
        });
    }

    @Test
    void createPositionReturnsCorrectPosition() {
        Position positionOne = SetupInputParser.createPosition("0 0");
        Position positionTwo = SetupInputParser.createPosition("15 14");
        Position positionThree = SetupInputParser.createPosition("13 100");

        assertAll(() -> {
            assertEquals(0,positionOne.getX());
            assertEquals(0,positionOne.getY());
            assertEquals(15,positionTwo.getX());
            assertEquals(14,positionTwo.getY());
            assertEquals(13,positionThree.getX());
            assertEquals(100,positionThree.getY());
        });
    }







}