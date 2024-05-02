package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.dtos.RentalOutputDto;
import com.group9.carrentalbackend.models.Rental;

import java.util.List;

public interface RentalService {
    RentalOutputDto createRental(RentalDto rentalDto);
    Rental getRentalById(Long id);
    List<Rental> getAllOngoingRental();
    List<Rental> getRentalHistoryByCustomerId(Long id);
    List<Rental> getRentalHistoryByVehicleId(Long id);
    List<Rental> getReservationByVehicleId(Long id);
    List<Rental> getReservationByCustomerId(Long id);
    Double getRentalCost(CostDto costDto);
    Rental cancelRentalById(Long id);
}
