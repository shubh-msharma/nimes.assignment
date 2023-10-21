package com.nimesa.assignment.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(){
        super("no entity found for given id");
    }

    public EntityNotFoundException(String entity, String entityId) {
        super("no " + entity + " found for given id: " + entityId);
    }
}
