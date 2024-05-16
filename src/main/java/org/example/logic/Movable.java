package org.example.logic;

import org.example.dataclasses.Instruction;

public interface Movable {

    void executeMovementInstructions(Instruction[] instructions);

}
