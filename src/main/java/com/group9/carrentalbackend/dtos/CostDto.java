package com.group9.carrentalbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CostDto {
    private Long id;
    private Date startDate;
    private Date endDate;

}
