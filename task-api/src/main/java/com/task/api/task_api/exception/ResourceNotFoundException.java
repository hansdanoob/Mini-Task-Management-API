package com.task.api.task_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String string) {
        super(string);
    }
}
