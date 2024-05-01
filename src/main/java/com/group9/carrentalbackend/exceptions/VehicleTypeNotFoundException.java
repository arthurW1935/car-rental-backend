package com.group9.carrentalbackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleTypeNotFoundException extends IllegalArgumentException{
    private String vehicleType;
    public VehicleTypeNotFoundException(String vehicleType, String message) {
        super(message);
        this.vehicleType = vehicleType;
    }
}
