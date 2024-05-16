package org.example;

import parsers.SetupInputParser;

public class Main {
    public static void main(String[] args) {
        PlateauSize p = SetupInputParser.createPlateauSize("9 9");
        Position position = SetupInputParser.createInitialPosition("1 2 S");

        System.out.println(p.getPlateauXSize());
        System.out.println(position);
    }
}