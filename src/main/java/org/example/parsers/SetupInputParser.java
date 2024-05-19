package org.example.parsers;

import org.example.dataclasses.Direction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.example.logic.DirectionalPosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupInputParser {

    public static PlateauSize createPlateauSize(String input) throws IllegalArgumentException {
        if (!createPlateauSizeInputIsValid(input)) {
            throw new IllegalArgumentException("Input invalid. \"X Y\" for values > 0");
        }
        String[] sizeParts = input.split(" ");
        if (Integer.parseInt(sizeParts[0]) < 1 || Integer.parseInt(sizeParts[1]) < 1) {
            throw new IllegalArgumentException("Input invalid. \"X Y\" for values > 0");
        }
        return new PlateauSize(Integer.parseInt(sizeParts[0]), Integer.parseInt(sizeParts[1]));
    }

    public static Position createDirectionalPosition(String input) {
        if (!createDirectionalPositionInputIsValid(input)) {
            throw new IllegalArgumentException("Input invalid. \"X Y D\" for X/Y >= 0, D = [N/E/S/W]");
        }
        String[] directionalPositionParts = input.split(" ");
        if (Integer.parseInt(directionalPositionParts[0]) < 0 || Integer.parseInt(directionalPositionParts[1]) < 0) {
            throw new IllegalArgumentException("Input invalid. \"X Y D\" for X/Y >= 0, D = [N/E/S/W]");
        }
        return new DirectionalPosition(Integer.parseInt(directionalPositionParts[0]),
                Integer.parseInt(directionalPositionParts[1]), getDirection(directionalPositionParts[2]));
    }

    public static Position createPosition(String input) {
        if (!createPositionInputIsValid(input)) {
            throw new IllegalArgumentException("Input invalid. \"X Y\" for values >= 0");
        }
        String[] positionParts = input.split(" ");
        if (Integer.parseInt(positionParts[0]) < 0 || Integer.parseInt(positionParts[1]) < 0) {
            throw new IllegalArgumentException("Input invalid. \"X Y\" for values >= 0");
        }
        return new Position(Integer.parseInt(positionParts[0]), Integer.parseInt(positionParts[1]));
    }

    private static boolean createPlateauSizeInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^\\d+ \\d+$").matcher(input);
        return matcher.find();
    }

    private static boolean createDirectionalPositionInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^\\d+ \\d+ [ENSW]$").matcher(input);
        return matcher.find();
    }

    private static boolean createPositionInputIsValid(String input) {
        Matcher matcher = Pattern.compile("^\\d+ \\d+$").matcher(input);
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
