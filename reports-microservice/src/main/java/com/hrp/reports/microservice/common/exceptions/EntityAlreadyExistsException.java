package com.hrp.reports.microservice.common.exceptions;

public class EntityAlreadyExistsException extends Exception{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
