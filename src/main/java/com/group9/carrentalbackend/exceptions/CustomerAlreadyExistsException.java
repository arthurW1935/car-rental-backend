package com.group9.carrentalbackend.exceptions;

import com.group9.carrentalbackend.models.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAlreadyExistsException  extends RuntimeException{
    private Customer customer;
    public CustomerAlreadyExistsException(Customer customer) {
        super("Customer with id " + customer.getId() + " already exists.");
        this.customer=customer;
    }
}
