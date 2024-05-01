package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.models.Rental;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @PostMapping("")
    public Rental createRental(@RequestBody Rental rental) {
        return null;
    }
    @GetMapping
    public Rental getRentalById(Long id){
        return null;
    }
    @GetMapping("")
    public List<Rental> getAllOngoingRental() {
        return null;
    }

    @GetMapping("/history/customer/{id}")
    public List<Rental> getRentalHistoryByCustomerId(Long id){
        return null;
    }
    @GetMapping("/history/vehicle/{id}")
    public List<Rental> getRentalHistoryByVehicleId(){
        return null;
    }

    @GetMapping("/cost")
    public Double getRentalCost(CostDto costDto){
        return null;
    }
    @DeleteMapping("/{id}")
    public Rental cancelRentalbyId(@PathVariable Long id){
        return null;
    }
}
