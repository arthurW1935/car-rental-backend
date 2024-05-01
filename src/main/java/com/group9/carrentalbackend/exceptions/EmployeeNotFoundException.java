package com.group9.carrentalbackend.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id, String message) {
        super(message);
    }
}
