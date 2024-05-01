package com.group9.carrentalbackend.exceptions;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException{

    private Long id;
    public CustomerNotFoundException(Long id, String message) {
        super(message);
        this.id=id;
    }
}
