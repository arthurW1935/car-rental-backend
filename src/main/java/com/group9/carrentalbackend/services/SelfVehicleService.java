package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.exceptions.InvalidVehicleTypeException;
import com.group9.carrentalbackend.exceptions.VehicleNotFoundException;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.models.Vehicle;
import com.group9.carrentalbackend.models.VehicleType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfVehicleService implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final BranchRepository branchRepository;

    public SelfVehicleService(VehicleRepository vehicleRepository, BranchRepository branchRepository) {
        this.vehicleRepository = vehicleRepository;
        this.branchRepository = branchRepository;
    }
    @Override
    public Vehicle getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()){
            throw new VehicleNotFoundException(id,"Vehicle not found");
        }
        return vehicle.get();
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        Branch branch = vehicle.getBranch();

        if(branch.getId() == null){
            vehicle.setBranch(branchRepository.save(branch));
        }

        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        Optional<Branch> optionalBranch = branchRepository.findById(vehicle1.getBranch().getId());

        if(optionalBranch.isEmpty()){
            throw new BranchNotFoundException(vehicle1.getBranch().getId(),"Branch not found");
        }

        vehicle1.setBranch(optionalBranch.get());
        return vehicle1;
    }

    @Override
    public Vehicle updateVehicleById(Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle.getId());
        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(vehicle.getId(),"Vehicle not found");
        }
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle removeVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(id,"Vehicle not found");
        }
        vehicleRepository.deleteById(id);
        return optionalVehicle.get();
    }

    @Override
    public List<Vehicle> getVehiclesByType(String type) {
        try {
            VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase());
            return vehicleRepository.findByType(vehicleType);
        } catch (IllegalArgumentException e) {
            throw new InvalidVehicleTypeException(type, "Invalid vehicle type");
        }
    }

    @Override
    public List<Vehicle> getVehiclesByBranch(Long branchId) {
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        if(optionalBranch.isEmpty()){
            throw new BranchNotFoundException(branchId,"Branch not found");
        }
        return vehicleRepository.findByBranch(optionalBranch.get());
    }
}
