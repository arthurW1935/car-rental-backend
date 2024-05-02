package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.dtos.RentalOutputDto;
import com.group9.carrentalbackend.models.Rental;
import com.group9.carrentalbackend.services.RentalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RentalController.class);
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
        LOGGER.info("RentalController.getRentalById: id: {}", id);
        return rentalService.getRentalById(id);
    }
    @GetMapping("")
    public List<RentalOutputDto> getAllRentals(){
        LOGGER.info("RentalController.getAllRentals");
        return rentalService.getAllRentals();
    }
    @GetMapping("/ongoing")
    public List<RentalOutputDto> getAllOngoingRental() {
        LOGGER.info("RentalController.getAllOngoingRental");
        return rentalService.getAllOngoingRental();
    }

    @GetMapping("/history/customer/{id}")
    public List<RentalOutputDto> getRentalHistoryByCustomerId(@PathVariable Long id){
        LOGGER.info("RentalController.getRentalHistoryByCustomerId: id: {}", id);
        return rentalService.getRentalHistoryByCustomerId(id);
    }
    @GetMapping("/history/vehicle/{id}")
    public List<RentalOutputDto> getRentalHistoryByVehicleId(@PathVariable Long id){
        LOGGER.info("RentalController.getRentalHistoryByVehicleId: id: {}", id);
        return rentalService.getRentalHistoryByVehicleId(id);
    }
    @GetMapping("/reservation/vehicle/{id}")
    public List<RentalOutputDto> getReservationByVehicleId(@PathVariable Long id){
        LOGGER.info("RentalController.getReservationByVehicleId: id: {}", id);
        return rentalService.getReservationByVehicleId(id);
    }
    @GetMapping("/reservation/customer/{id}")
    public List<RentalOutputDto> getReservationByCustomerId(@PathVariable Long id){
        LOGGER.info("RentalController.getReservationByCustomerId: id: {}", id);
        return rentalService.getReservationByCustomerId(id);
    }
    @GetMapping("/cost")
    public Double getRentalCost(
            @RequestParam Long vehicleId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        LOGGER.info("RentalController.getRentalCost: vehicleId: {}, startDate: {}, endDate: {}", vehicleId, startDate, endDate);
        return rentalService.getRentalCost(vehicleId, startDate, endDate);
    }
    @DeleteMapping("/{id}")
    public RentalOutputDto cancelRentalById(@PathVariable Long id){
        LOGGER.info("RentalController.cancelRentalById: id: {}", id);
        return rentalService.cancelRentalById(id);
    }
}
