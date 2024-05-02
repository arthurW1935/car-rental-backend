package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.dtos.RentalOutputDto;
import com.group9.carrentalbackend.models.Rental;
import com.group9.carrentalbackend.services.RentalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;
    RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }
    @PostMapping("")
    public RentalOutputDto createRental(@RequestBody RentalDto rentalDto) {
        return rentalService.createRental(rentalDto);
    }
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Long id){
        return rentalService.getRentalById(id);
    }
    @GetMapping("/ongoing")
    public List<Rental> getAllOngoingRental() {
        return rentalService.getAllOngoingRental();
    }

    @GetMapping("/history/customer/{id}")
    public List<Rental> getRentalHistoryByCustomerId(@PathVariable Long id){
        return rentalService.getRentalHistoryByCustomerId(id);
    }
    @GetMapping("/history/vehicle/{id}")
    public List<Rental> getRentalHistoryByVehicleId(@PathVariable Long id){
        return rentalService.getRentalHistoryByVehicleId(id);
    }
    @GetMapping("/reservation/vehicle/{id}")
    public List<Rental> getReservationByVehicleId(@PathVariable Long id){
        return rentalService.getReservationByVehicleId(id);
    }
    @GetMapping("/reservation/customer/{id}")
    public List<Rental> getReservationByCustomerId(@PathVariable Long id){
        return rentalService.getReservationByCustomerId(id);
    }
    @GetMapping("/cost")
    public Double getRentalCost(CostDto costDto){
        return rentalService.getRentalCost(costDto);
    }
    @DeleteMapping("/{id}")
    public Rental cancelRentalById(@PathVariable Long id){
        return rentalService.cancelRentalById(id);
    }
}
