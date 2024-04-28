package com.smit.api.exception;

public class ProcedureNotFoundException extends Exception  {
    public ProcedureNotFoundException() {
        super("Procedure not found");
    }
}
