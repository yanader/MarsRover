package org.example.logic;

public class PositionOccupiedException extends Exception{
    public PositionOccupiedException(String errorMessage){
        super(errorMessage);
    }
}
