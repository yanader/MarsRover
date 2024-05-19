package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinerTest {

    @Test
    void roverExtendsVehicleAndImplementsDiggable() {
        Vehicle miner = new Miner(new Position(0, 0));
        assertEquals(Vehicle.class, miner.getClass().getSuperclass());
        assertEquals(Miner.class, miner.getClass());
        assertEquals(Diggable.class, miner.getClass().getInterfaces()[0]);
    }

    @Test
    void minerReportsPosition() {
        Vehicle miner = new Miner(new Position(1, 2));

        assertAll(() -> {
           assertEquals(1, miner.getPosition().getX());
           assertEquals(2, miner.getPosition().getY());
        });
    }

    @Test
    void minerMines() {
        Miner miner = new Miner(new Position(1, 2));

        assertEquals(Resource.GOLD.getClass(), miner.dig(new Instruction[]{Instruction.D}).getClass());
    }




}