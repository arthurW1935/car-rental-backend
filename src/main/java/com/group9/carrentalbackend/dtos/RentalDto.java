package com.group9.carrentalbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentalDto {
    private Long id;
    private Long vehicleId;
    private Long customerId;
    private Date startDate;
    private Date endDate;
}
