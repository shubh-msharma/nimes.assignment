package com.nimesa.assignment.exceptions;

public class MissingRequiredParamException extends RuntimeException {
    public MissingRequiredParamException(){
        super("missing required param");
    }

    public MissingRequiredParamException(String property) {
        super("missing required param : " + property);
    }
}
