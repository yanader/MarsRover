package org.example.userinterface;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.example.logic.Movable;
import org.example.logic.Plateau;
import org.example.logic.Vehicle;
import org.example.logic.Rover;
import org.example.parsers.InstructionParser;
import org.example.parsers.SetupInputParser;

import java.util.Scanner;

public class CommandCentre {
    private Scanner scanner;
    private Plateau plateau;
    private Vehicle activeVehicle;

    public CommandCentre() {
        this.scanner = new Scanner(System.in);
        startMission();
    }

    public void startMission() {
        System.out.println("Welcome to Mars");
        missionSetup();
        while (true) {
            Instruction[] instructions = takeInstruction();
            if (instructions == null) {
                System.out.println("Thanks for visiting Mars. Please come back soon!");
                return;
            }
            if (plateau.instructionSetIsPossible(activeVehicle, instructions)) {
                executeInstruction(activeVehicle, instructions);
                System.out.println("Vehicle type: " + activeVehicle.getClass().getSimpleName() + " now at " + activeVehicle.reportPosition());
            } else {
                System.out.println("I'm sorry, this instruction set causes a collision and can not be executed.");
            }
        }
    }

    private void missionSetup() {
        createPlateau();
        dropRover();
    }

    private void createPlateau() {
        while (true) {
            System.out.println("Input plateau size. (Format:NUM NUM)");
            try {
                PlateauSize ps = SetupInputParser.createPlateauSize(scanner.nextLine());
                plateau = new Plateau(ps);
                System.out.println("Exploring plateau of size " + ps.getPlateauXSize() + " * " + ps.getPlateauYSize());
                System.out.println("x-Axis: 0 - " + (ps.getPlateauXSize() - 1));
                System.out.println("y-Axis: 0 - " + (ps.getPlateauYSize() - 1));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for plateau size");
            }
        }
    }

    private void dropRover() {
        while (true) {
            System.out.println("Input vehicle drop site. (Format: X Y D)");
            System.out.println("X -> X Coordinate");
            System.out.println("Y -> Y Coordinate");
            System.out.println("D -> Cardinal compass directions -> N/E/S/W");
            try {
                Position pos = SetupInputParser.createInitialPosition(scanner.nextLine());
                Vehicle rover = new Rover(pos);
                plateau.landVehicle(rover);
                this.activeVehicle = rover;
                System.out.println("Vehicle type: " + rover.getClass().getSimpleName() + " launched at " + rover.reportPosition());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for initial position");
            }
        }
    }

    private Instruction[] takeInstruction() {
        while(true) {
            System.out.println("Provide the vehicle with an instruction");
            System.out.println("'H' for help, 'Q' to quit");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("H")) {
                help();
                continue;
            }
            if (input.equalsIgnoreCase("Q")) {
                return null;
            }
            try {
                return InstructionParser.createInstructionList(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid instruction input");
            }
        }
    }

    private void executeInstruction(Vehicle vehicle, Instruction[] instructions) {
        if (vehicle instanceof Movable) {
            Movable mover = (Movable)vehicle;
            mover.executeMovementInstructions(instructions);
        }
    }

    private void help() {
        System.out.println("Vehicle instructions are provided through a chain of uppercase characters");
        System.out.println("L -> rotate 90 degrees left/anti-clockwise");
        System.out.println("R -> rotate 90 degrees right/clockwise");
        System.out.println("M -> move forward one space");
        System.out.println("Example:");
        System.out.println("\tInput - MMRMMLMM");
        System.out.println("\t\tMoves forward two spaces");
        System.out.println("\t\tRotates right");
        System.out.println("\t\tMoves forward two spaces");
        System.out.println("\t\tRotates left");
        System.out.println("\t\tMoves forward two spaces");

    }

    /*
    Instruction truncateInstruction() {
      This will be the first "beyond MVP" implementation that
      offers the user a truncated version of an invalid instruction
      if the controlled rover was to hit an object or the edge of the plateau
    }
    */



}