package com.shashank.app.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id) {
        super("Could not find record " + id);
    }
}
