package com.group9.carrentalbackend.exceptions;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
