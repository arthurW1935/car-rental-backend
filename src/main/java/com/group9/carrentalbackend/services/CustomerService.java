package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    List<Customer> getAllCustomers();

    Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;

    Customer updateCustomer(Customer customer) throws CustomerNotFoundException;

    Customer deleteCustomer(Long id) throws CustomerNotFoundException;

    List<Rental> getCustomerHistory(Long id) throws CustomerNotFoundException;
}
