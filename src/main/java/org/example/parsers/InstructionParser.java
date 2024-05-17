package org.example.parsers;

import org.example.dataclasses.Instruction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionParser {

    public static Instruction[] createMovementInstructionList(String input) throws IllegalArgumentException {
        if (!createMovementInstructionListInputIsValid(input)) {
            throw new IllegalArgumentException("Invalid input. L/R/M accepted");
        }
        Instruction[] instructionStream = new Instruction[input.length()];
        for (int i = 0; i < input.length(); i++) {
            instructionStream[i] = convertCharToInstruction(input.charAt(i));
        }
        return instructionStream;
    }

    private static boolean createMovementInstructionListInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^[RLM]+$").matcher(input);
        return matcher.find();
    }

    private static Instruction convertCharToInstruction(char c) {
        if (c == 'L') return Instruction.L;
        if (c == 'R') return Instruction.R;
        if (c == 'M') return Instruction.M;
        if (c == 'D') return Instruction.D;
        return null;
    }

}
