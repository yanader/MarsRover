package org.example.parsers;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.logic.Miner;
import org.example.logic.Rover;
import org.example.logic.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    Vehicle rover;
    Vehicle miner;

    @BeforeEach
    void setup() {
        miner = new Miner(new Position(1, 1, Direction.S));
        rover = new Rover(new Position(0, 0, Direction.N));
    }

    @Test
    void createInstructionListReturnsMovementInstructionArray() {
        assertEquals(Instruction[].class, InstructionParser.createMovementInstructionList("LRM", rover).getClass());
    }

    @Test
    void createMovementInstructionListRejectsInvalidInput() {
        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("", rover));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("LRF", rover));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("Q", rover));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("!", rover));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("L R M", rover));
        });
    }

    @Test
    void createMovementInstructionListAcceptsValidInput() {
        assertAll(() -> {
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("L", rover));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("R", rover));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("M", rover));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("LMR", rover));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("RMLRLMRMLRLMRMLRMRMRLRMLR", rover));
        });
    }

    @Test
    void createMovementInstructionThrowsWhenGivenWrongVehicleType() {

        assertAll(() ->{
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("LMR", miner));
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("MMM", miner));
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("RRR", miner));
        });
    }

    @Test
    void createMovementInstructionListReturnsCorrectList() {
        Instruction[] testInstructionSetOne = {Instruction.L, Instruction.M, Instruction.R};
        Instruction[] testInstructionSetTwo = {Instruction.M, Instruction.M, Instruction.M};
        Instruction[] testInstructionSetThree = {Instruction.M};

        assertAll(() -> {
           assertArrayEquals(testInstructionSetOne, InstructionParser.createMovementInstructionList("LMR", rover));
           assertArrayEquals(testInstructionSetTwo, InstructionParser.createMovementInstructionList("MMM", rover));
           assertArrayEquals(testInstructionSetThree, InstructionParser.createMovementInstructionList("M", rover));
        });
    }

    @Test
    void createDigInstructionThrowsWithIncorrectInputs() {

        assertAll(() -> {
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createDigInstructionFromInput("D", rover));
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createDigInstructionFromInput("DD", miner));
            assertThrows(IllegalArgumentException.class, () -> InstructionParser.createDigInstructionFromInput("MMLM", miner));
        });
    }

    @Test
    void createDigInstructionAcceptsCorrectInputs() {
        assertDoesNotThrow(() -> InstructionParser.createDigInstructionFromInput("D", miner));
    }
}