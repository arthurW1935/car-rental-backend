package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.dtos.VehicleDto;
import com.group9.carrentalbackend.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import com.group9.carrentalbackend.models.Vehicle;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("")
    public List<VehicleDto> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping("")
    public VehicleDto createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public VehicleDto updateVehicleById(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicleById(vehicle);
    }

    @DeleteMapping("/{id}")
    public VehicleDto removeVehicleById(@PathVariable Long id) {
        return vehicleService.removeVehicleById(id);
    }

    @GetMapping("/type")
    public List<VehicleDto> getVehiclesByType(@RequestParam String type) {
        return vehicleService.getVehiclesByType(type);
    }

    @GetMapping("/branch/{id}/")
    public List<VehicleDto> getVehiclesByBranch(@PathVariable Long id) {
        return vehicleService.getVehiclesByBranch(id);
    }

    @GetMapping("/status")
    public List<VehicleDto> getVehiclesByStatus(@RequestParam String status) {
        return vehicleService.getVehiclesByType(status);
    }
}
