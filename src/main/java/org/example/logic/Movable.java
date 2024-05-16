package org.example.logic;

import org.example.dataclasses.Direction;
import org.example.dataclasses.Instruction;

public interface Movable {

    void move(Instruction[] instructions);

}
