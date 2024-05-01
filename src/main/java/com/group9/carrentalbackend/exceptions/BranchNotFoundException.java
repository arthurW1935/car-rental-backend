package com.group9.carrentalbackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchNotFoundException extends RuntimeException{
    private Long id;
    public BranchNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }
}
