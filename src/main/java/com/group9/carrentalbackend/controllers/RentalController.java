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
    public RentalOutputDto getRentalById(@PathVariable Long id){
        return rentalService.getRentalById(id);
    }
    @GetMapping("/ongoing")
    public List<RentalOutputDto> getAllOngoingRental() {
        return rentalService.getAllOngoingRental();
    }

    @GetMapping("/history/customer/{id}")
    public List<RentalOutputDto> getRentalHistoryByCustomerId(@PathVariable Long id){
        return rentalService.getRentalHistoryByCustomerId(id);
    }
    @GetMapping("/history/vehicle/{id}")
    public List<RentalOutputDto> getRentalHistoryByVehicleId(@PathVariable Long id){
        return rentalService.getRentalHistoryByVehicleId(id);
    }
    @GetMapping("/reservation/vehicle/{id}")
    public List<RentalOutputDto> getReservationByVehicleId(@PathVariable Long id){
        return rentalService.getReservationByVehicleId(id);
    }
    @GetMapping("/reservation/customer/{id}")
    public List<RentalOutputDto> getReservationByCustomerId(@PathVariable Long id){
        return rentalService.getReservationByCustomerId(id);
    }
    @GetMapping("/cost")
    public Double getRentalCost(CostDto costDto){
        return rentalService.getRentalCost(costDto);
    }
    @DeleteMapping("/{id}")
    public RentalOutputDto cancelRentalById(@PathVariable Long id){
        return rentalService.cancelRentalById(id);
    }
}
