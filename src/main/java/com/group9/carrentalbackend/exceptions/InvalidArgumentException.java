package com.group9.carrentalbackend.exceptions;

public class InvalidArgumentException extends RuntimeException{
    private Long id;
    public InvalidArgumentException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
