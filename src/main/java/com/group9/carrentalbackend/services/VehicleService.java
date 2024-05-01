package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.models.VehicleType;
import com.group9.carrentalbackend.models.Vehicle;

import java.util.List;

public interface VehicleService {
    public Vehicle getVehicleById(Long id);
    public List<Vehicle> getAllVehicles();
    public Vehicle createVehicle(Vehicle vehicle);
    public Vehicle updateVehicleById(Vehicle vehicle);
    public Vehicle removeVehicleById(Long id);
    public List<Vehicle> getVehiclesByType(String vehicleType);
    public List<Vehicle> getVehiclesByBranch(Long branchId);
    public List<Vehicle> getVehiclesByStatus(String status);
}
