package com.nimesa.assignment.exceptions;

public class ActionNotAllowedException extends RuntimeException {

    public ActionNotAllowedException(){
        super("request is not allowed");
    }

    public ActionNotAllowedException(String action,String reason){
        super("action : " + action + " not allowed, reason : " + reason);
    }
}
