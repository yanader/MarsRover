package org.example.logic;

import org.example.dataclasses.Instruction;
import org.example.dataclasses.Resource;

public interface Diggable {

    Resource dig(Instruction[] instructions);

}
