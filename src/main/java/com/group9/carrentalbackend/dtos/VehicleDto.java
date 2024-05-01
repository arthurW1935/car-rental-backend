package com.group9.carrentalbackend.dtos;

import com.group9.carrentalbackend.models.VehicleStatus;
import com.group9.carrentalbackend.models.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String manufacturer;
    private String model;
    private Integer year;
    private String licensePlateNumber;
    private Double currentMileage;
    private VehicleType vehicleType;
    private VehicleStatus vehicleStatus;
    private Long branchId;
}
