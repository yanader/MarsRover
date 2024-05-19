package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Resource;

public interface Diggable {

    public Resource dig(Instruction[] instructions);
}
