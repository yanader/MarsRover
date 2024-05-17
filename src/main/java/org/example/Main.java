package org.example;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.logic.Rover;
import org.example.logic.Vehicle;
import org.example.parsers.InstructionParser;
import org.example.userinterface.CommandCentre;


public class Main {
    public static void main(String[] args) {
//        String input = "RMLM";
//        Instruction[] instructions = InstructionParser.createInstructionList(input);
//        Vehicle rover = new Rover(new Position(0, 0, Direction.N));
//
//
//        System.out.println(rover.reportPosition());
//
//        // This is the typecasting I'll need to use to call a Rover method on a Vehicle
//        // Presumably this will be handled in the command centre method.
//        Rover r = (Rover)rover;
//        r.executeMovementInstructions(instructions);
//
//        System.out.println(rover.reportPosition());

        CommandCentre cc = new CommandCentre();

    }
}