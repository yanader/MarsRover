package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;

public interface Movable {

    void move(Instruction[] instructions);

    boolean testMovement(Plateau plateau, Instruction[] instructions);

    Instruction[] truncateMovementSet(Plateau plateau, Instruction[] instructions);

    boolean moveForwardIsPossible(DirectionalPosition position, Plateau plateau);

    Direction getDirection();
}
