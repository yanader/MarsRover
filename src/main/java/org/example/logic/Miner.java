package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;

public class Miner extends Vehicle implements Diggable{
    private int drillCharges;

    public Miner(Position position) {
        super(position);
    }

    @Override
    public void executeDiggingInstructions(Instruction[] instructions) {

    }
}
