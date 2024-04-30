package com.group9.carrentalbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group9.carrentalbackend.models.Type;

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
    public void removeVehicleById(@PathVariable Long id) {
        return;
    }

    @GetMapping("/type")
    public List<Vehicle> getVehiclesByType(@RequestBody Type type) {
        return null;
    }

    @GetMapping("/branch/{id}")
    public List<Vehicle> getVehiclesByBranch(@PathVariable Long id) {
        return null;
    }
}
