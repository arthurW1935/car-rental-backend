package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.VehicleDto;
import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.exceptions.InvalidVehicleTypeException;
import com.group9.carrentalbackend.exceptions.VehicleNotFoundException;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.models.Vehicle;
import com.group9.carrentalbackend.models.VehicleStatus;
import com.group9.carrentalbackend.models.VehicleType;
import com.group9.carrentalbackend.repositories.BranchRepository;
import com.group9.carrentalbackend.repositories.VehicleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfVehicleService implements VehicleService{
    private final VehicleRepository vehicleRepository;
    private final BranchRepository branchRepository;
    private final SelfBranchService selfBranchService;

    public SelfVehicleService(VehicleRepository vehicleRepository, BranchRepository branchRepository, SelfBranchService selfBranchService) {
        this.vehicleRepository = vehicleRepository;
        this.branchRepository = branchRepository;
        this.selfBranchService = selfBranchService;
    }
    @Override
    public VehicleDto getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()){
            throw new VehicleNotFoundException(id,"Vehicle not found");
        }
        Vehicle thisProject = vehicle.get();
        return new VehicleDto(thisProject.getId(), thisProject.getManufacturer(), thisProject.getModel(), thisProject.getYear(), thisProject.getLicensePlateNumber(), thisProject.getCurrentMileage(), thisProject.getVehicleType(), thisProject.getVehicleStatus(), thisProject.getBranch().getId());
    }

    @Override
    public List<VehicleDto> getAllVehicles()

    {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<VehicleDto> vehicleDtoList =  new java.util.ArrayList<>();
        for(Vehicle vehicle: vehicleList){
            vehicleDtoList.add(new VehicleDto(vehicle.getId(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlateNumber(), vehicle.getCurrentMileage(), vehicle.getVehicleType(), vehicle.getVehicleStatus(), vehicle.getBranch().getId()));
        }
        return vehicleDtoList;
    }

    @Override
    public VehicleDto createVehicle(Vehicle vehicle) {
        Branch branch = vehicle.getBranch();

        if(branch == null){
            throw new InvalidArgumentException("Branch must not be empty");
        }

        if(branch.getId() == null){
            Long newBranchId = selfBranchService.createBranch(branch).getId();
            vehicle.setBranch(branchRepository.findById(newBranchId).get());
        }

        Vehicle vehicle1 = vehicleRepository.save(vehicle);
        return new VehicleDto(vehicle1.getId(), vehicle1.getManufacturer(), vehicle1.getModel(), vehicle1.getYear(), vehicle1.getLicensePlateNumber(), vehicle.getCurrentMileage(), vehicle.getVehicleType(), vehicle.getVehicleStatus(), vehicle.getBranch().getId());
    }

    @Override
    public VehicleDto updateVehicleById(Vehicle vehicle) {

        if(vehicle.getId() == null){
            throw new InvalidArgumentException("Vehicle id must not be empty");
        }
        if(vehicleRepository.findById(vehicle.getId()).isEmpty()){
            throw new VehicleNotFoundException(vehicle.getId(),"Vehicle not found");
        }
        if(vehicle.getBranch() == null){
            throw new InvalidArgumentException("Branch id must not be empty");
        }
        if(branchRepository.findById(vehicle.getBranch().getId()).isEmpty()){
            throw new BranchNotFoundException(vehicle.getBranch().getId(),"Branch not found");
        }
        Vehicle thisVehicle = vehicleRepository.save(vehicle);
        return new VehicleDto(thisVehicle.getId(), thisVehicle.getManufacturer(), thisVehicle.getModel(), thisVehicle.getYear(), thisVehicle.getLicensePlateNumber(), thisVehicle.getCurrentMileage(), thisVehicle.getVehicleType(), thisVehicle.getVehicleStatus(), thisVehicle.getBranch().getId());
    }

    @Override
    public VehicleDto removeVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(id,"Vehicle not found");
        }
        vehicleRepository.deleteById(id);
        return new VehicleDto(optionalVehicle.get().getId(), optionalVehicle.get().getManufacturer(), optionalVehicle.get().getModel(), optionalVehicle.get().getYear(), optionalVehicle.get().getLicensePlateNumber(), optionalVehicle.get().getCurrentMileage(), optionalVehicle.get().getVehicleType(), optionalVehicle.get().getVehicleStatus(), optionalVehicle.get().getBranch().getId());
    }

    @Override
    public List<VehicleDto> getVehiclesByType(String type) {
        List<VehicleDto> vehicleDtoList =  new java.util.ArrayList<>();
        try {
            VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase());
            List<Vehicle> vehicleList = vehicleRepository.findAllByVehicleType(vehicleType);
            for(Vehicle vehicle: vehicleList){
                vehicleDtoList.add(new VehicleDto(vehicle.getId(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlateNumber(), vehicle.getCurrentMileage(), vehicle.getVehicleType(), vehicle.getVehicleStatus(), vehicle.getBranch().getId()));
            }
        } catch (Exception e) {
            throw new InvalidVehicleTypeException(type, "Invalid vehicle type");
        }
        return vehicleDtoList;
    }

    @Override
    public List<VehicleDto> getVehiclesByBranch(Long branchId) {
        Optional<Branch> optionalBranch = branchRepository.findById(branchId);
        if(optionalBranch.isEmpty()){
            throw new BranchNotFoundException(branchId,"Branch not found");
        }
        List<Vehicle> vehicleList = vehicleRepository.findAllByBranchId(branchId);
        List<VehicleDto> vehicleDtoList =  new java.util.ArrayList<>();
        for(Vehicle vehicle: vehicleList){
            vehicleDtoList.add(new VehicleDto(vehicle.getId(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlateNumber(), vehicle.getCurrentMileage(), vehicle.getVehicleType(), vehicle.getVehicleStatus(), vehicle.getBranch().getId()));
        }
        return vehicleDtoList;
    }

    @Override
    public List<VehicleDto> getVehiclesByStatus(String status) {
        List<VehicleDto> vehicleDtoList =  new java.util.ArrayList<>();
        try {
            VehicleStatus vehicleStatus = VehicleStatus.valueOf(status.toUpperCase());
            List <Vehicle>  thisVehicle= vehicleRepository.findAllByVehicleStatus(vehicleStatus);
            for (Vehicle vehicle : thisVehicle) {
                vehicleDtoList.add(new VehicleDto(vehicle.getId(), vehicle.getManufacturer(), vehicle.getModel(), vehicle.getYear(), vehicle.getLicensePlateNumber(), vehicle.getCurrentMileage(), vehicle.getVehicleType(), vehicle.getVehicleStatus(), vehicle.getBranch().getId()));
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidVehicleTypeException(status, "Invalid Vehicle Status");
        }
        return vehicleDtoList;
    }

}
