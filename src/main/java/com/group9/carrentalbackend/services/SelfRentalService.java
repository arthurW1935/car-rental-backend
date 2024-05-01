package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.exceptions.RentalNotAvailableException;
import com.group9.carrentalbackend.exceptions.RentalNotFoundException;
import com.group9.carrentalbackend.exceptions.VehicleNotFoundException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import com.group9.carrentalbackend.models.Vehicle;
import com.group9.carrentalbackend.repositories.CustomerRepository;
import com.group9.carrentalbackend.repositories.RentalRepository;
import com.group9.carrentalbackend.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SelfRentalService extends RentalService{
    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;

    public SelfRentalService(RentalRepository rentalRepository, VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    public Rental getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isEmpty()) {
            throw new RentalNotFoundException(id, "Rental not found");
        }
        return rental.get();
    }

    public Rental createRental(RentalDto rentalDto) {
        Date startDate = rentalDto.getStartDate();
        Date endDate = rentalDto.getEndDate();

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(rentalDto.getVehicleId());

        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException(rentalDto.getVehicleId(), "Vehicle not found");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(rentalDto.getCustomerId());

        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(rentalDto.getCustomerId(), "Customer not found");
        }

        if(!createRentalCheck(rentalDto.getVehicleId(), startDate, endDate)){
            throw new RentalNotAvailableException(rentalDto.getVehicleId(), startDate, endDate, "Vehicle not available for rental");
        }

        CostDto costDto = new CostDto(rentalDto.getVehicleId(), startDate, endDate);

        Double totalCost = getRentalCost(costDto);

        Rental rental = new Rental(rentalDto.getId(), optionalVehicle.get(), optionalCustomer.get(), startDate, endDate, totalCost);
        Rental savedRental = rentalRepository.save(rental);

        return savedRental;
    }

    private boolean createRentalCheck(Long vehicleId, Date startDate, Date endDate){
        List<Rental> rentals = rentalRepository.findByVehicleId(vehicleId);
        for(Rental rental : rentals){
            if(rental.getStartDate().before(endDate) && rental.getEndDate().after(startDate)){
                return false;
            }
        }
        return true;
    }

    public List<Rental> getRentalHistoryByCustomerId(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException(customerId, "Customer not found");
        }

        return rentalRepository.findByCustomerId(customerId);
    }

    public List<Rental> getRentalHistoryByVehicleId(Long vehicleId){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(vehicleId, "Vehicle not found");
        }

        return rentalRepository.findByVehicleId(vehicleId);
    }

}
