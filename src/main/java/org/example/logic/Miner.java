package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;

public class Miner extends Vehicle implements Diggable{

    public Miner(Position position) {
        super(position);
    }

    public Resource dig(Instruction[] instructions) {
        if (instructions[0] == Instruction.D) {
            return Resource.digForResource();
        } else {
            throw new IllegalArgumentException("Not a valid instruction for a Diggable");
        }
    }
}
