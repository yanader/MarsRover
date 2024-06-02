package org.example.userinterface;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.PlateauSize;
import org.example.dataclasses.Resource;
import org.example.logic.Miner;
import org.example.logic.Rover;
import org.example.logic.Vehicle;
import org.example.parsers.InstructionParser;
import org.example.parsers.SetupInputParser;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    final private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }



    public int takeUserOption() {
        while(true) {
            System.out.println("Please select an option");
            System.out.println("1. Launch Vehicle");
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

    public Vehicle chooseActiveVehicle(List<Vehicle> vehicleList) {
        while (true) {
            System.out.println("Please select a vehicle by number");
            for (int i = 0; i < vehicleList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". Type: " + vehicleList.get(i).getClass().getSimpleName() + " | " + vehicleList.get(i).outputPosition());
            }
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                return vehicleList.get(choice - 1);
            } catch (Exception ignore) {}
        }
    }

    public PlateauSize getPlateauSize() {
        while (true) {
            System.out.println("Input plateau size. (Format:NUM NUM)");
            try {
                PlateauSize ps = SetupInputParser.createPlateauSize(scanner.nextLine());
                System.out.println("Plateau size " + ps.plateauXSize() + "*" + ps.plateauYSize() +" | x:(0-" + (ps.plateauXSize() - 1) + ") | y:(0-" + (ps.plateauYSize() - 1) + ")");
                return ps;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for plateau size");
            }
        }
    }

    public int specifyVehicleType() {
        // There's an improvement here that I can make by introducing
        // an Enum of vehicle types
        while (true){
            try {
                System.out.println("Please choose a vehicle type:");
                System.out.println("1. Rover");
                System.out.println("2. Miner");
                int choice =  Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) continue;
                return choice;
            } catch (Exception ignore) {}
        }
    }

    public String getPosition() {
        while (true) {
            try {
                System.out.println("Input vehicle drop site. (Format: X Y) -> [X] Coordinate -> [Y] Coordinate");
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input can not be empty");
            }
        }
    }

    public String getDirectionalPosition() {
        while (true) {
            try {
                System.out.println("Input drop site. (Format: X Y D) -> [X] Coordinate -> [Y] Coordinate -> [D]irection (N/E/S/W)");
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input can not be empty");
            }
        }
    }

    public Instruction[] promptForInstructions(Vehicle activeVehicle) {
        while(true) {
            System.out.println("Active vehicle type - " + activeVehicle.getClass().getSimpleName());
            System.out.println("Provide the vehicle with an instruction ('H' for help)" );
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("H")) {
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
                System.out.println("Invalid Instruction Input");
            }

        }
    }

    public boolean userConfirmsTruncatedInstructions(Instruction[] truncatedInstructions) {
        while (true) {
            System.out.println("The truncated instruction set that can be successfully executed is: ");
            System.out.println(Arrays.toString(truncatedInstructions));
            System.out.println("Would you like to execute this command? (Y to proceed, N to reject)");
            if (scanner.nextLine().equalsIgnoreCase("Y")) {
                return true;
            } else if (scanner.nextLine().equalsIgnoreCase("N")) {
                System.out.println("Instruction Cancelled");
                return false;
            }
        }
    }

    public void reportVehicleLaunch(Vehicle vehicle) {
        System.out.println("Vehicle type: " + vehicle.getClass().getSimpleName() + " launched at " + vehicle.getPosition());
    }

    public void reportActiveVehicle(Vehicle vehicle) {
        System.out.println("Active Vehicle type: " + vehicle.getClass().getSimpleName() + " at " + vehicle.getPosition());
    }

    public void welcomeMessage() {
        System.out.println("Welcome to Mars");
    }

    public void endMessage() {
        System.out.println("Thank you for visiting Mars. Please come back soon");
    }

    public void confirmMove(Vehicle activeVehicle) {
        System.out.println("Vehicle type: " + activeVehicle.getClass().getSimpleName() + " now at " + activeVehicle.getPosition());
    }

    public void confirmDig(Vehicle activeVehicle, Resource resource) {
        System.out.println("Vehicle type: " + activeVehicle.getClass().getSimpleName() + " found " + resource.name() + " at " + activeVehicle.getPosition());
    }

    public void collisionWarning() {
        System.out.println("I'm sorry, this instruction set causes a collision and can not be executed.");
    }

    private void help() {
        System.out.println("Vehicle instructions are provided through a chain of uppercase characters");
        System.out.println("L -> rotate 90 degrees left/anti-clockwise");
        System.out.println("R -> rotate 90 degrees right/clockwise");
        System.out.println("M -> move forward one space");
        System.out.println("D -> dig");
        System.out.println("Example:");
        System.out.println("\tInput - MMRMMLMM");
        System.out.println("\t\tMoves forward two spaces");
        System.out.println("\t\tRotates right");
        System.out.println("\t\tMoves forward two spaces");
        System.out.println("\t\tRotates left");
        System.out.println("\t\tMoves forward two spaces");
    }
}
