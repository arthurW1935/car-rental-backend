package com.group9.carrentalbackend.services;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.exceptions.CustomerNotFoundException;
import com.group9.carrentalbackend.exceptions.VehicleNotFoundException;
import com.group9.carrentalbackend.models.Customer;
import com.group9.carrentalbackend.models.Rental;
import com.group9.carrentalbackend.models.Vehicle;
import com.group9.carrentalbackend.repositories.CustomerRepository;
import com.group9.carrentalbackend.repositories.RentalRepository;
import com.group9.carrentalbackend.repositories.VehicleRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SelfRentalService implements RentalService{
    CustomerRepository customerRepository;
    RentalRepository rentalRepository;
    VehicleRepository vehicleRepository;
    public SelfRentalService(CustomerRepository customerRepository, RentalRepository rentalRepository, VehicleRepository vehicleRepository){
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }
    @Override
    public Rental createRental(Rental rental) {
        return null;
    }

    @Override
    public Rental getRentalById(Long id) {
        return null;
    }

    @Override
    public List<Rental> getAllOngoingRental() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        List<Rental> ans = rentalRepository.findAllByStartDateBeforeAndEndDateAfter(today,today);
        return ans;
    }

    @Override
    public List<Rental> getRentalHistoryByCustomerId(Long id) {
        return null;
    }

    @Override
    public List<Rental> getRentalHistoryByVehicleId(Long id) {

        return null;
    }

    @Override
    public List<Rental> getReservationByVehicleId(Long id) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        Vehicle vehicle = vehicleOptional.orElseThrow(() -> new VehicleNotFoundException(id, "Invalid Id"));
        List<Rental> ans = rentalRepository.findAllByStartDateAfterAndVehicle(today,vehicle);
        return ans;
    }

    @Override
    public List<Rental> getReservationByCustomerId(Long id) {
        Calendar calender = Calendar.getInstance();
        Date today = calender.getTime();
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException(id));
        List<Rental> ans = rentalRepository.findAllByStartDateAfterAndCustomer(today,customer);
        return ans;
    }

    @Override
    public Double getRentalCost(CostDto costDto) {
        int b = 200;
        Long id = costDto.getId();
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        Vehicle vehicle = vehicleOptional.orElseThrow(() -> new VehicleNotFoundException(id, "Invalid Id"));

        Date startDate = costDto.getStartDate();
        Date endDate = costDto.getEndDate();

        long numberOfDays = ChronoUnit.DAYS.between((Temporal) startDate, (Temporal) endDate);
        Double cost = (double) 0;int a = 100;
        if(vehicle.getVehicleType().equals("TWO_WHEELER")) {
            cost += numberOfDays * a;
        }
        else{
             cost += numberOfDays*b;
        }
        return cost;
    }


    @Override
    public Rental cancelRentalById(Long id) {
        Rental rental = rentalRepository.getReferenceById(id);
        rentalRepository.deleteById(id);
        return rental;
    }
}
