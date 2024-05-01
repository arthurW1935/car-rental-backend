package com.group9.carrentalbackend.exceptions;

import java.util.Date;

public class RentalNotAvailableException extends RuntimeException{
    private Long id;
    private Date startDate;
    private Date endDate;
    public RentalNotAvailableException(Long id, Date startDate, Date endDate, String message) {
        super(message);
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
