package org.example.dataclasses;

public enum Instruction {
    L,
    R,
    M,
    D;

    public String getInstructionAsString() {
        return this.name();
    }
}
