package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.services.VehicleService;
import org.springframework.web.bind.annotation.*;

import com.group9.carrentalbackend.models.Vehicle;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping("")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicleById(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicleById(vehicle);
    }

    @DeleteMapping("/{id}")
    public Vehicle removeVehicleById(@PathVariable Long id) {
        return vehicleService.removeVehicleById(id);
    }

    @GetMapping("/type")
    public List<Vehicle> getVehiclesByType(@RequestParam String type) {
        return vehicleService.getVehiclesByType(type);
    }

    @GetMapping("/branch/{id}")
    public List<Vehicle> getVehiclesByBranch(@PathVariable Long id) {
        return vehicleService.getVehiclesByBranch(id);
    }

    @GetMapping("/status")
    public List<Vehicle> getVehiclesByStatus(@RequestParam String status) {
        return vehicleService.getVehiclesByType(status);
    }
}
