package org.example.userinterface;

import org.example.dataclasses.Instruction;
import org.example.logic.Plateau;

import java.util.Scanner;

public class CommandCentre {
    Scanner scanner;
    Plateau plateau;

    public CommandCentre(Scanner scanner) {
        this.scanner = scanner;
        startMission();
    }

    public void startMission() {

    }

    private void missionSetup() {

    }

    private void createPlateau() {

    }

    private void dropRover() {

    }

    private boolean takeInstruction() {
        return false;
    }

    private boolean checkInstruction(){
        return false;
    }

    private void executeInstruction() {
    }

    /*
    Instruction truncateInstruction() {
      This will be the first "beyond MVP" implementation that
      offers the user a truncated version of an invalid instruction
      if the controlled rover was to hit an object or the edge of the plateau
    }
    */



}
