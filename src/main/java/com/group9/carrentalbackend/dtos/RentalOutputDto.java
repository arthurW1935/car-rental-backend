package com.group9.carrentalbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalOutputDto {
    private Long id;
    private Long vehicleId;
    private Long customerId;
    private Date startDate;
    private Date endDate;
    private Double totalCost;
}

