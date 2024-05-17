package org.example.parsers;

import org.example.dataclasses.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    @Test
    void createInstructionListReturnsMovementInstructionArray() {
        assertEquals(Instruction[].class, InstructionParser.createMovementInstructionList("LRM").getClass());
    }

    @Test
    void createMovementInstructionListRejectsInvalidInput() {
        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList(""));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("LRF"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("Q"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("!"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createMovementInstructionList("L R M"));
        });
    }

    @Test
    void createMovementInstructionListAcceptsValidInput() {
        assertAll(() -> {
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("L"));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("R"));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("M"));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("LMR"));
            assertDoesNotThrow(() -> InstructionParser.createMovementInstructionList("RMLRLMRMLRLMRMLRMRMRLRMLR"));
        });
    }

    @Test
    void createMovementInstructionListReturnsCorrectList() {
        Instruction[] testInstructionSetOne = {Instruction.L, Instruction.M, Instruction.R};
        Instruction[] testInstructionSetTwo = {Instruction.M, Instruction.M, Instruction.M};
        Instruction[] testInstructionSetThree = {Instruction.M};

        assertAll(() -> {
           assertArrayEquals(testInstructionSetOne, InstructionParser.createMovementInstructionList("LMR"));
           assertArrayEquals(testInstructionSetTwo, InstructionParser.createMovementInstructionList("MMM"));
           assertArrayEquals(testInstructionSetThree, InstructionParser.createMovementInstructionList("M"));
        });
    }
}