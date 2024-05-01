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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SelfRentalService implements RentalService{
    CustomerRepository customerRepository;
    RentalRepository rentalRepository;
    VehicleRepository vehicleRepository;
    public SelfRentalService(CustomerRepository customerRepository, RentalRepository rentalRepository, VehicleRepository vehicleRepository){
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }
    @Override
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

    @Override
    public Rental getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isEmpty()) {
            throw new RentalNotFoundException(id, "Rental not found");
        }
        return rental.get();
    }

    @Override
    public List<Rental> getAllOngoingRental() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        List<Rental> ans = rentalRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(today,today);
        return ans;
    }

    @Override
    public List<Rental> getRentalHistoryByCustomerId(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException(customerId, "Customer not found");
        }

        return rentalRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Rental> getRentalHistoryByVehicleId(Long vehicleId){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(vehicleId, "Vehicle not found");
        }

        return rentalRepository.findByVehicleId(vehicleId);
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
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException(id, "Invalid Id"));
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
