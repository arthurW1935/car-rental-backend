package com.group9.carrentalbackend.exceptions;

public class EmployeeAlreadyExistsException extends Exception {
    public EmployeeAlreadyExistsException(Long id , String message) {
        super(message);
    }
}
