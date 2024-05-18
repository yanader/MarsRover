package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Position;
import org.example.dataclasses.Resource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinerTest {

    @Test
    void roverExtendsVehicleAndImplementsDiggable() {
        Vehicle miner = new Miner(new Position(0, 0, Direction.N));
        assertEquals(Vehicle.class, miner.getClass().getSuperclass());
        assertEquals(Miner.class, miner.getClass());
        assertEquals(Diggable.class, miner.getClass().getInterfaces()[0]);
    }

    @Test
    void minerReportsPosition() {
        Vehicle miner = new Miner(new Position(1, 2, Direction.N));

        assertAll(() -> {
           assertEquals(1, miner.getPosition().x());
           assertEquals(2, miner.getPosition().y());
           assertEquals(Direction.N, miner.getPosition().direction());
        });
    }

    @Test
    void minerMines() {
        Miner miner = new Miner(new Position(1, 2, Direction.N));

        assertEquals(Resource.GOLD.getClass(), miner.dig().getClass());
    }




}