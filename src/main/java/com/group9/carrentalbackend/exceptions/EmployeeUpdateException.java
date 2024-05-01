package com.group9.carrentalbackend.exceptions;

public class EmployeeUpdateException extends Throwable {
    public EmployeeUpdateException(String message, Exception e) {
        super(message);
    }
}
