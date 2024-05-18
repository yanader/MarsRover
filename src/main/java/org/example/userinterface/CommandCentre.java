package org.example.userinterface;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;
import org.example.logic.*;
import org.example.parsers.InstructionParser;
import org.example.parsers.SetupInputParser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandCentre {
    final private Scanner scanner;
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

            int choice = takeUserOption();

            switch(choice) {
                case 0:
                    System.out.println("Thank you for visiting Mars. Please come back soon");
                    return;
                case 1:
                    dropVehicle();
                    continue;
                case 2:
                    activateVehicle();
                    continue;
                case 3:
                    Instruction[] instructions = takeInstruction();
                    executeInstruction(activeVehicle, instructions);
            }
        }
    }

    private void activateVehicle() {
        List<Vehicle> vehicleList = plateau.getVehicles();
        while (true) {
            System.out.println("Please select a vehicle by number");
            for (int i = 0; i < vehicleList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". Type: " + vehicleList.get(i).getClass().getSimpleName() + " | " + vehicleList.get(i).shortPosition());
            }
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > vehicleList.size()) continue;
                activeVehicle = vehicleList.get(choice - 1);
                return;
            } catch (Exception ignore) {}
        }
    }

    private Instruction[] takeInstruction() {
        while(true) {
            System.out.println("Active vehicle type - " + activeVehicle.getClass().getSimpleName());
            System.out.println("Provide the vehicle with an instruction");
            System.out.println("'H' for help");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("H")) {
                help();
                continue;
            }
            try {
                if (activeVehicle.getClass() == Rover.class) {
                    return InstructionParser.createMovementInstructionList(input, activeVehicle);
                } else if (activeVehicle.getClass() == Miner.class) {
                    return InstructionParser.createDigInstructionFromInput(input, activeVehicle);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid instruction input");
            }
        }
    }

    private void missionSetup() {
        createPlateau();
        dropVehicle();
    }

    private void createPlateau() {
        while (true) {
            System.out.println("Input plateau size. (Format:NUM NUM)");
            try {
                PlateauSize ps = SetupInputParser.createPlateauSize(scanner.nextLine());
                plateau = new Plateau(ps);
                System.out.println("Exploring plateau of size " + ps.plateauXSize() + " * " + ps.plateauYSize());
                System.out.println("x-Axis: 0 - " + (ps.plateauXSize() - 1));
                System.out.println("y-Axis: 0 - " + (ps.plateauYSize() - 1));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for plateau size");
            }
        }
    }

    private void dropVehicle() {
        while (true) {
            System.out.println("Input vehicle drop site. (Format: X Y D)");
            System.out.println("X -> X Coordinate");
            System.out.println("Y -> Y Coordinate");
            System.out.println("D -> Cardinal compass directions -> N/E/S/W");
            try {
                Position pos = SetupInputParser.createInitialPosition(scanner.nextLine());
                int vehicleType = specifyVehicleType();
                Vehicle vehicle = vehicleBuilder(vehicleType, pos);
                if (vehicle == null) continue;
                plateau.landVehicle(vehicle);
                this.activeVehicle = vehicle;
                System.out.println("Vehicle type: " + vehicle.getClass().getSimpleName() + " launched at " + vehicle.reportPosition());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for initial position");
            } catch (PositionOccupiedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int specifyVehicleType() {
        while (true){
            try {
                System.out.println("Please choose a vehicle type");
                System.out.println("1. Rover");
                System.out.println("2. Miner");
                int choice =  Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) continue;
                return choice;
            } catch (Exception ignore) {}
        }
    }

    private Vehicle vehicleBuilder(int i, Position pos){
        if (i == 1) return new Rover(pos);
        if (i == 2) return new Miner(pos);
        return null;
    }

    private int takeUserOption() {
        while(true) {
            System.out.println("Please select an option");
            System.out.println("1. Land Vehicle");
            System.out.println("2. Activate Vehicle");
            System.out.println("3. Control Vehicle");
            System.out.println("0. Quit Programme");
            try {
                int i = Integer.parseInt(scanner.nextLine());
                if (i < 0 || i > 3) {
                    continue;
                }
                return i;
            } catch (Exception e) {
                System.out.println("Invalid Choice");
            }
        }
    }

    private void executeInstruction(Vehicle vehicle, Instruction[] instructions) {
        if (vehicle instanceof Movable mover) {
            if (plateau.movementSetIsPossible(activeVehicle, instructions)) {
                mover.move(instructions);
            } else {
                System.out.println("I'm sorry, this instruction set causes a collision and can not be executed.");
                offerTruncatedInstructionsAsMove(activeVehicle, instructions);
            }
            System.out.println("Vehicle type: " + activeVehicle.getClass().getSimpleName() + " now at " + activeVehicle.reportPosition());
        } else if (vehicle instanceof Diggable digger) {
            Resource resource = digger.dig();
            System.out.println("Vehicle type: " + activeVehicle.getClass().getSimpleName() + " found " + resource.name() + " at " + activeVehicle.reportPosition());
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


    private Instruction[] truncateInstruction(Vehicle vehicle, Instruction[] instructions) {
        return plateau.truncateMovementInstructions(vehicle, instructions);
    }

    private void offerTruncatedInstructionsAsMove(Vehicle vehicle, Instruction[] instructions) {
        Instruction[] truncatedInstructions = truncateInstruction(vehicle, instructions);
        if (truncatedInstructions.length > 0) {
            System.out.println("The truncated instruction set that can be executed successfully is: ");
            while (true) {
                System.out.println(Arrays.toString(truncatedInstructions));
                System.out.println("Would you like to execute this command? (Y to proceed, N to reject)");
                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    executeInstruction(vehicle, truncatedInstructions);
                    break;
                } else if (scanner.nextLine().equalsIgnoreCase("N")) {
                    System.out.println("Instruction Cancelled");
                    break;
                }
            }

        }
    }




}
