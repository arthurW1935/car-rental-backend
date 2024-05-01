package com.group9.carrentalbackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidVehicleTypeException extends IllegalArgumentException{
    private String vehicleType;
    public InvalidVehicleTypeException(String vehicleType, String message) {
        super(message);
        this.vehicleType = vehicleType;
    }
}
