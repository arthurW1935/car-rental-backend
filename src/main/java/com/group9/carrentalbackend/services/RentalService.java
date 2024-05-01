package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.models.Rental;

import java.util.List;

public interface RentalService {
    Rental createRental(Rental rental);
    Rental getRentalById(Long id);
    List<Rental> getAllOngoingRental();
    List<Rental> getRentalHistoryByCustomerId(Long id);
    List<Rental> getRentalHistoryByVehicleId(Long id);
    List<Rental> getReservationByVehicleId(Long id);
    List<Rental> getReservationByCustomerId(Long id);
    Double getRentalCost(CostDto costDto);
    Rental cancelRentalById(Long id);
}
