package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.models.Rental;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return null;
    }
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id){
        return null;
    }
    @GetMapping("/ongoing")
    public List<Rental> getAllOngoingRental() {
        return null;
    }

    @GetMapping("/history/customer/{id}")
    public List<Rental> getRentalHistoryByCustomerId(@PathVariable Long id){
        return null;
    }
    @GetMapping("/history/vehicle/{id}")
    public List<Rental> getRentalHistoryByVehicleId(@PathVariable Long id){
        return null;
    }

    @PostMapping("/cost")
    public Double getRentalCost(@RequestBody CostDto costDto){
        return null;
    }
    @DeleteMapping("/{id}")
    public Rental cancelRentalById(@PathVariable Long id){
        return null;
    }
}
