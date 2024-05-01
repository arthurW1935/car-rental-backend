package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping("/{id}")
    public Customer getCustomerbyId(@PathVariable Long id) {
        return null;
    }
    @GetMapping
    public List<Customer> getAllCustomers() {
        return null;
    }
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return null;
    }
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return null;
}
    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable Long id) {
        return null;
    }
    @GetMapping("/{id}/history")
    public List<Rental> getCustomerHistory(@PathVariable Long id) {
        return null;
    }

}

