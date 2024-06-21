package org.example.userinterface;

import org.example.database.DB;
import org.example.dataclasses.*;
import org.example.logic.*;
import org.example.output.ReportWriter;
import org.example.parsers.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CommandCentre {
    private Plateau plateau;
    private Vehicle activeVehicle;
    private final UserInterface userInterface;

    public CommandCentre() {
        this.userInterface = new UserInterface();
        DB.setup();
        startMission();
    }

    public void startMission() {
        userInterface.welcomeMessage();
        missionSetup();
        while (true) {

            int choice = userInterface.takeUserOption();

            switch(choice) {
                case 0:
                    userInterface.endMessage();
                    try {
                        ReportWriter reportWriter = new ReportWriter();
                    } catch (IOException | SQLException e) {
                        System.out.println("Report writing failed");
                    }
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
        activeVehicle = userInterface.chooseActiveVehicle(vehicleList);
        userInterface.reportActiveVehicle(activeVehicle);
    }

    private Instruction[] takeInstruction() {
        return userInterface.promptForInstructions(activeVehicle);
    }

    private void missionSetup() {
        createPlateau();
        dropVehicle();
    }

    private void createPlateau() {
        plateau = new Plateau(userInterface.getPlateauSize());
    }

    private void dropVehicle() {
        while (true) {
            try {
                int vehicleType = userInterface.specifyVehicleType();
                Vehicle vehicle = vehicleBuilder(vehicleType);
                if (vehicle == null) continue;
                plateau.landVehicle(vehicle);
                this.activeVehicle = vehicle;
                userInterface.reportVehicleLaunch(activeVehicle);
                DB.logNewVehicle(activeVehicle);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input for initial position");
            } catch (PositionOccupiedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Vehicle vehicleBuilder(int i){
        if (i == 1) {
            DirectionalPosition directPos = SetupInputParser.createDirectionalPosition(userInterface.getDirectionalPosition());
            return new Rover(directPos);
        }
        if (i == 2) {
            Position pos = SetupInputParser.createPosition(userInterface.getPosition());
            return new Miner(pos);
        }
        return null;
    }

    private void executeInstruction(Vehicle vehicle, Instruction[] instructions) {
        if (vehicle instanceof Movable mover) {
            movableMoves(mover, instructions);
        } else if (vehicle instanceof Diggable digger) {
            diggableDigs(digger, instructions);
        }
    }

    private void movableMoves(Movable movable, Instruction[] instructions) {
        if (plateau.movementSetIsPossible((Movable)activeVehicle, instructions)) {
            movable.move(instructions);
            DB.logNewInstruction(activeVehicle, instructions);
            userInterface.confirmMove(activeVehicle);
        } else {
            userInterface.collisionWarning();
            offerTruncatedInstructionsAsMove(activeVehicle, instructions);
        }
    }

    private void diggableDigs(Diggable diggable, Instruction[] instructions) {
        Resource resource = diggable.dig(instructions);
        DB.logNewInstruction(activeVehicle, instructions);
        DB.logNewResource(activeVehicle, resource);
        userInterface.confirmDig(activeVehicle, resource);
    }


    private Instruction[] truncateInstruction(Movable movable, Instruction[] instructions) {
        return movable.truncateMovementSet(plateau, instructions);
    }

    private void offerTruncatedInstructionsAsMove(Vehicle vehicle, Instruction[] instructions) {
        if (vehicle instanceof Movable) {
            Instruction[] truncatedInstructions = truncateInstruction((Movable) vehicle, instructions);
            if (truncatedInstructions.length > 0 && userInterface.userConfirmsTruncatedInstructions(truncatedInstructions)) {
                executeInstruction(vehicle, truncatedInstructions);
            }
        }
    }




}
