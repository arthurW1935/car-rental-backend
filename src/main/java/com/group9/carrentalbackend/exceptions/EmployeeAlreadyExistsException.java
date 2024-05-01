package com.group9.carrentalbackend.exceptions;

public class EmployeeAlreadyExistsException extends Exception {
    private Long id;
    public EmployeeAlreadyExistsException(Long id , String message) {
        super(message);
        this.id = id;
    }
}
