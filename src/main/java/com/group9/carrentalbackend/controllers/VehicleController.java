package com.group9.carrentalbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.group9.carrentalbackend.models.VehicleType;

import com.group9.carrentalbackend.models.Vehicle;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("")
    public List<Vehicle> getAllVehicles() {
        return null;
    }

    @PostMapping("")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return null;
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicleById(@RequestBody Vehicle vehicle) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Vehicle removeVehicleById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/type")
    public List<Vehicle> getVehiclesByType(@RequestParam String type) {
        return null;
    }

    @GetMapping("/branch/{id}")
    public List<Vehicle> getVehiclesByBranch(@PathVariable Long id) {
        return null;
    }
}
