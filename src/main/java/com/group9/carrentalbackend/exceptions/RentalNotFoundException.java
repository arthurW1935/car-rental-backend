package com.group9.carrentalbackend.exceptions;

public class RentalNotFoundException extends RuntimeException{
    private Long id;
    public RentalNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
