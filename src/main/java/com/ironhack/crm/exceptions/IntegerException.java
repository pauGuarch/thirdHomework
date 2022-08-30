package com.ironhack.crm.exceptions;

public class IntegerException extends Exception{
    public IntegerException(){
        super("The number should be greater than zero.");
    }
}
