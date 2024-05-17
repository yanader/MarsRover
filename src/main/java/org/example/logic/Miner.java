package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;

public class Miner extends Vehicle implements Diggable{
    private int drillCharges;

    public Miner(Position position) {
        super(position);
    }

    @Override
    public Resource dig() {
        return Resource.digForResource();
    }
}
