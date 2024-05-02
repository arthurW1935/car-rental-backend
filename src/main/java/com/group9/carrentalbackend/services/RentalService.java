package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.dtos.RentalOutputDto;
import com.group9.carrentalbackend.models.Rental;

import java.util.List;

public interface RentalService {
    RentalOutputDto createRental(RentalDto rentalDto);
    RentalOutputDto getRentalById(Long id);
    List<RentalOutputDto> getAllOngoingRental();
    List<RentalOutputDto> getRentalHistoryByCustomerId(Long id);
    List<RentalOutputDto> getRentalHistoryByVehicleId(Long id);
    List<RentalOutputDto> getReservationByVehicleId(Long id);
    List<RentalOutputDto> getReservationByCustomerId(Long id);
    Double getRentalCost(CostDto costDto);
    RentalOutputDto cancelRentalById(Long id);
}
