package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service("SelfCustomerService")
public class SelfCustomerService implements CustomerService{
   private final CustomerRepository customerRepository;
   private final RentalRepository rentalRepository;
   SelfCustomerService(CustomerRepository customerRepository, RentalRepository rentalRepository) {
       this.rentalRepository = rentalRepository;
       this.customerRepository = customerRepository;
   }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            return null;   // TODO: throw exception
        }

        return customer.get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());
        if(existingCustomer.isEmpty()) {
            return null;    // TODO: throw exception
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()) {
                 return null;       // TODO: throw exception
        }

        customerRepository.deleteById(id);
        return existingCustomer.get();

    }

    @Override
    public List<Rental> getCustomerHistory(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isEmpty()) {
            return null;       // TODO: throw exception
        }

      return rentalRepository.findbyCustomer(id);
    }

}
