package com.ironhack.crm.exceptions;

public class EmptyStringException extends Exception{

    public EmptyStringException() {
      super("You must enter some data.");
    }
}
