package parsers;

import org.example.dataclasses.Direction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
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
                    assertEquals(10, plateauSizeOne.getPlateauXSize());
                    assertEquals(10, plateauSizeOne.getPlateauYSize());
                    assertEquals(1, plateauSizeTwo.getPlateauXSize());
                    assertEquals(100, plateauSizeTwo.getPlateauYSize());
                    assertEquals(15, plateauSizeThree.getPlateauXSize());
                    assertEquals(16, plateauSizeThree.getPlateauYSize());
                });
    }

    // Testing for initial position input parsing

    @Test
    void createInitialPositionReturnsPosition() {
        assertEquals(Position.class, SetupInputParser.createInitialPosition("5 5 N").getClass());
    }

    @Test
    void createInitialPositionRejectsInvalidInput() {

        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createInitialPosition("1 1"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createInitialPosition("1 1 F"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createInitialPosition("11N"));
            assertThrows(IllegalArgumentException.class, () -> SetupInputParser.createInitialPosition("-1 1 N"));
        });
    }

    @Test
    void createInitialPositionAcceptsValidInputs() {
        assertAll(() -> {
            assertDoesNotThrow(() -> SetupInputParser.createInitialPosition("1 1 N"));
            assertDoesNotThrow(() -> SetupInputParser.createInitialPosition("0 0 E"));
            assertDoesNotThrow(() -> SetupInputParser.createInitialPosition("100 17 W"));
            assertDoesNotThrow(() -> SetupInputParser.createInitialPosition("0 50 S"));
        });
    }

    @Test
    void createInitialPositionReturnsCorrectPosition() {
            Position positionOne = SetupInputParser.createInitialPosition("0 0 N");
            Position positionTwo = SetupInputParser.createInitialPosition("15 14 S");
            Position positionThree = SetupInputParser.createInitialPosition("13 100 W");

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





}