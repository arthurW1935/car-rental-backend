package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.CustomerAlreadyExistException;
import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import com.group9.carrentalbackend.repositories.CustomerRepository;
import com.group9.carrentalbackend.repositories.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
            throw new CustomerNotFoundException(id, "Customer not found");
        }

        return customer.get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public Customer addCustomer(Customer customer)  {

       if(customer.getId()!=null){
           Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
           if(existingCustomer.isPresent()) {
               throw new CustomerAlreadyExistException(customer.getId(),"Customer Already exists");
           }
           else{
               throw new InvalidArgumentException("Id should not be provided");
           }
       }

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if(existingCustomer.isEmpty()) {
           throw new CustomerNotFoundException(customer.getId(), "Customer not found");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()) {
                 throw new CustomerNotFoundException(id, "Customer not found");
        }

        customerRepository.deleteById(id);
        return existingCustomer.get();

    }

}
