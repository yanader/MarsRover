package org.example.parsers;

import org.example.dataclasses.Instruction;
import org.example.logic.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class InstructionParser {

    public static Instruction[] createMovementInstructionList(String input, Vehicle vehicle) throws IllegalArgumentException {
        if (!Arrays.asList(vehicle.getClass().getInterfaces()).contains(Movable.class)) throw new IllegalArgumentException("Incorrect vehicle type for movement instructions");
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
        return matcher.find() && !input.isEmpty();
    }

    public static Instruction[] createDigInstructionFromInput(String input, Vehicle vehicle) throws IllegalArgumentException {
        if (!Arrays.asList(vehicle.getClass().getInterfaces()).contains(Diggable.class)) throw new IllegalArgumentException("Incorrect vehicle type for digging instructions");
        if (input.equalsIgnoreCase("D")) return new Instruction[]{Instruction.D};
        throw new IllegalArgumentException("Instruction invalid.");
    }

    private static Instruction convertCharToInstruction(char c) {
        if (c == 'L') return Instruction.L;
        if (c == 'R') return Instruction.R;
        if (c == 'M') return Instruction.M;
        if (c == 'D') return Instruction.D;
        return null;
    }

}
