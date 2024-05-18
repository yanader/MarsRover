package org.example.logic;

import org.example.dataclasses.Instruction;

public interface Movable {

    void move(Instruction[] instructions);

    boolean testMovement(Plateau plateau, Instruction[] instructions);
}
