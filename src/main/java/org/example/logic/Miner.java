package org.example.logic;

import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;

public class Miner extends Vehicle implements Diggable{

    public Miner(Position position) {
        super(position);
    }

    public Resource dig() {
        return Resource.digForResource();
    }
}
