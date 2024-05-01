package com.group9.carrentalbackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleNotFoundException extends RuntimeException{
    private Long id;
    public VehicleNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
