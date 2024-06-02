package org.example.userinterface;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.example.logic.DirectionalPosition;
import org.example.logic.Miner;
import org.example.logic.Rover;
import org.example.logic.Vehicle;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserInterfaceTest {

//    @Mock
    Scanner mockScanner = Mockito.mock(Scanner.class);

    @InjectMocks
    UserInterface userInterface;

    @BeforeEach
    void setup() throws Exception {
        userInterface = new UserInterface();
        Field scannerField = UserInterface.class.getDeclaredField("scanner");
        scannerField.setAccessible(true);
        scannerField.set(userInterface, mockScanner);
    }

    @Test
    void takeUserOptionReturnsCorrectInt() {
        when(mockScanner.nextLine()).thenReturn("3").thenReturn("2").thenReturn("1").thenReturn("0");
        assertAll(() -> {
            assertEquals(3, userInterface.takeUserOption());
            assertEquals(2, userInterface.takeUserOption());
            assertEquals(1, userInterface.takeUserOption());
            assertEquals(0, userInterface.takeUserOption());
        });
    }

    @Test
    void takeUserOptionPromptsAgainAfterInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("5").thenReturn("3");
        assertEquals(3, userInterface.takeUserOption());
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void returnVehicleReturnsCorrectVehicle() {
        Rover rover = new Rover(new DirectionalPosition(0, 0, Direction.N));
        Miner miner = new Miner(new Position(0, 0));
        List<Vehicle> vehicleList = List.of(
                rover,
                miner
        );
        when(mockScanner.nextLine()).thenReturn("1").thenReturn("2");
        assertAll(() -> {
            assertEquals(rover, userInterface.chooseActiveVehicle(vehicleList));
            assertEquals(miner, userInterface.chooseActiveVehicle(vehicleList));
        });
    }

    @Test
    void getPlateauSizeReturnsPlateauSize() {
        when(mockScanner.nextLine()).thenReturn("10 10");
        assertEquals(PlateauSize.class, userInterface.getPlateauSize().getClass());
    }

    @Test
    void getPlateauSizePromptsAgainAfterInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("10 10 N").thenReturn("5 5");
        assertEquals(PlateauSize.class, userInterface.getPlateauSize().getClass());
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void specifyVehicleTypeReturnsCorrectInt() {
        when(mockScanner.nextLine()).thenReturn("2").thenReturn("1");
        assertAll(() -> {
            assertEquals(2, userInterface.specifyVehicleType());
            assertEquals(1, userInterface.specifyVehicleType());
        });
    }

    @Test
    void specifyVehicleTypePromptsAgainAfterInvalidInput() {
        when(mockScanner.nextLine()).thenReturn("5").thenReturn("2");
        assertEquals(2, userInterface.specifyVehicleType());
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void getPositionRejectsEmptyInput() {
        when(mockScanner.nextLine()).thenReturn("").thenReturn("5 5");
        assertEquals("5 5", userInterface.getPosition());
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void getDirectionalPositionRejectsEmptyInput() {
        when(mockScanner.nextLine()).thenReturn("").thenReturn("5 5 N");
        assertEquals("5 5 N", userInterface.getDirectionalPosition());
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void promptForInstructionReturnsAppropriateInstruction() {
        when(mockScanner.nextLine()).thenReturn("MLM").thenReturn("D");
        Instruction[] expectedOutputOne = {Instruction.M, Instruction.L, Instruction.M};
        Instruction[] expectedOutputTwo = {Instruction.D};

        assertAll(() -> {
            assertArrayEquals(expectedOutputOne, userInterface.promptForInstructions(new Rover(new DirectionalPosition(0, 0, Direction.N))));
            assertArrayEquals(expectedOutputTwo, userInterface.promptForInstructions(new Miner(new Position(0, 0))));
        });

    }

















}