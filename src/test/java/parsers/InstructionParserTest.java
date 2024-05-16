package parsers;

import org.example.dataclasses.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    @Test
    void createInstructionListReturnsInstructionArray() {
        assertEquals(Instruction[].class, InstructionParser.createInstructionList("LRM").getClass());
    }

    @Test
    void createInstructionListRejectsInvalidInput() {
        assertAll(() -> {
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createInstructionList(""));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createInstructionList("LRF"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createInstructionList("Q"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createInstructionList("!"));
           assertThrows(IllegalArgumentException.class, () -> InstructionParser.createInstructionList("L R M"));
        });
    }

    @Test
    void createInstructionListAcceptsValidInput() {
        assertAll(() -> {
            assertDoesNotThrow(() -> InstructionParser.createInstructionList("L"));
            assertDoesNotThrow(() -> InstructionParser.createInstructionList("R"));
            assertDoesNotThrow(() -> InstructionParser.createInstructionList("M"));
            assertDoesNotThrow(() -> InstructionParser.createInstructionList("LMR"));
            assertDoesNotThrow(() -> InstructionParser.createInstructionList("RMLRLMRMLRLMRMLRMRMRLRMLR"));
        });
    }

    @Test
    void createInstructionListReturnsCorrectList() {
        Instruction[] testInstructionSetOne = {Instruction.L, Instruction.M, Instruction.R};
        Instruction[] testInstructionSetTwo = {Instruction.M, Instruction.M, Instruction.M};
        Instruction[] testInstructionSetThree = {Instruction.M};

        assertAll(() -> {
           assertArrayEquals(testInstructionSetOne, InstructionParser.createInstructionList("LMR"));
           assertArrayEquals(testInstructionSetTwo, InstructionParser.createInstructionList("MMM"));
           assertArrayEquals(testInstructionSetThree, InstructionParser.createInstructionList("M"));
        });
    }
}