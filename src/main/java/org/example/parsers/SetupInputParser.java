package org.example.parsers;

import org.example.dataclasses.Direction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupInputParser {

    public static PlateauSize createPlateauSize(String input) throws IllegalArgumentException {
        if (!createPlateauSizeInputIsValid(input)) {
            throw new IllegalArgumentException("Input invalid. (\"I I\" for I > 0");
        }
        String[] sizes = input.split(" ");
        return new PlateauSize(Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1]));
    }

    public static Position createInitialPosition(String input) {
        if (!createInitialPositionInputIsValid(input)) {
            throw new IllegalArgumentException("Input invalid. (\"I I D\" for I >= 0, D = [NSEW]");
        }
        String[] instructions = input.split(" ");
        return new Position(Integer.parseInt(instructions[0]),
                Integer.parseInt(instructions[1]),
                getDirection(instructions[2]));
    }

    private static boolean createPlateauSizeInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^\\d+ \\d+$").matcher(input);
        return matcher.find();
    }

    private static boolean createInitialPositionInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^\\d+ \\d+ [ENSW]$").matcher(input);
        return matcher.find();
    }

    private static Direction getDirection(String direction) {
        return switch (direction) {
            case "N" -> Direction.N;
            case "E" -> Direction.E;
            case "S" -> Direction.S;
            case "W" -> Direction.W;
            default -> null;
        };
    }

}
