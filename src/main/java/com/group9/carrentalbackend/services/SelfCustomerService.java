package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfCustomerService")

public class SelfCustomerService implements CustomerService{
   private final CustomerRepository customerRepository;
   private final RentalRepository rentalRepository;
   SelfCustomerService(CustomerRepository customerRepository, RentalRepository rentalRepository) {
       this.rentalRepository = rentalRepository;
       this.customerRepository = customerRepository;
   }

    @Override
    public Customer getCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }

        return customer.get();
    }

    @Override
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    @Override
    public Customer addCustomer(Customer customer){

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer)  throws CustomerNotFoundException{
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if(existingCustomer.isEmpty()) {
           throw new CustomerNotFoundException(customer.getId());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) throws CustomerNotFoundException{
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()) {
                 throw new CustomerNotFoundException(id);
        }

        customerRepository.deleteById(id);
        return existingCustomer.get();

    }

    @Override
    public List<Rental> getCustomerHistory(Long id)  throws CustomerNotFoundException{
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

      return rentalRepository.findbyCustomer(id);
    }

}
