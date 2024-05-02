package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.dtos.VehicleDto;
import com.group9.carrentalbackend.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import com.group9.carrentalbackend.models.Vehicle;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;
    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable Long id) {
        LOGGER.info("VehicleController.getVehicleById: id: {}", id);
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("")
    public List<VehicleDto> getAllVehicles() {
        LOGGER.info("VehicleController.getAllVehicles");
        return vehicleService.getAllVehicles();
    }

    @PostMapping("")
    public VehicleDto createVehicle(@RequestBody Vehicle vehicle) {
        LOGGER.info("VehicleController.createVehicle: vehicle: {}", vehicle);
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("")
    public VehicleDto updateVehicleById(@RequestBody Vehicle vehicle) {
        LOGGER.info("VehicleController.updateVehicleById: vehicle: {}", vehicle);
        return vehicleService.updateVehicleById(vehicle);
    }

    @DeleteMapping("/{id}")
    public VehicleDto removeVehicleById(@PathVariable Long id) {
        LOGGER.info("VehicleController.removeVehicleById: id: {}", id);
        return vehicleService.removeVehicleById(id);
    }

    @GetMapping("/type")
    public List<VehicleDto> getVehiclesByType(@RequestParam String type) {
        LOGGER.info("VehicleController.getVehiclesByType: type: {}", type);
        return vehicleService.getVehiclesByType(type);
    }

    @GetMapping("/branch/{id}/")
    public List<VehicleDto> getVehiclesByBranch(@PathVariable Long id) {
        LOGGER.info("VehicleController.getVehiclesByBranch: id: {}", id);
        return vehicleService.getVehiclesByBranch(id);
    }

    @GetMapping("/status")
    public List<VehicleDto> getVehiclesByStatus(@RequestParam String status) {
        LOGGER.info("VehicleController.getVehiclesByStatus: status: {}", status);
        return vehicleService.getVehiclesByStatus(status);
    }
}
