package com.group9.carrentalbackend.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    private Long id;
    public EmployeeNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
