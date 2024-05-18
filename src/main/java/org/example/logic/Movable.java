package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Position;

public interface Movable {

    void move(Instruction[] instructions);

    boolean testMovement(Plateau plateau, Instruction[] instructions);

    Instruction[] truncateMovementSet(Plateau plateau, Instruction[] instructions);

    boolean moveForwardIsPossible(Position position, Plateau plateau);
}
