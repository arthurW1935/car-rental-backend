package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.CustomerAlreadyExistException;
import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer deleteCustomer(Long id);

    List<Rental> getCustomerHistory(Long id);
}
