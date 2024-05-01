package com.group9.carrentalbackend.exceptions;

public class CustomerAlreadyExistException extends RuntimeException{
    private Long id;
    public CustomerAlreadyExistException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
