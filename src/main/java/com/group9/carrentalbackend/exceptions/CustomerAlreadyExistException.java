package com.group9.carrentalbackend.exceptions;

public class CustomerAlreadyExistException extends Exception{
    public CustomerAlreadyExistException(Long id, String message) {
        super(message);
    }
}
