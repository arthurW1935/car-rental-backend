package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.VehicleDto;
import com.group9.carrentalbackend.models.VehicleType;
import com.group9.carrentalbackend.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    public VehicleDto getVehicleById(Long id);
    public List<VehicleDto> getAllVehicles();
    public VehicleDto createVehicle(Vehicle vehicle);
    public VehicleDto updateVehicleById(Vehicle vehicle);
    public VehicleDto removeVehicleById(Long id);
    public List<VehicleDto> getVehiclesByType(String vehicleType);
    public List<VehicleDto> getVehiclesByBranch(Long branchId);
    public List<VehicleDto> getVehiclesByStatus(String status);
}
