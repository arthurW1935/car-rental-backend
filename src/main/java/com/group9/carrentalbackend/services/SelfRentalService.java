package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.CostDto;
import com.group9.carrentalbackend.dtos.RentalDto;
import com.group9.carrentalbackend.dtos.RentalOutputDto;
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
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

@Service
public class SelfRentalService implements RentalService{
    CustomerRepository customerRepository;
    RentalRepository rentalRepository;
    VehicleRepository vehicleRepository;

    public SelfRentalService(CustomerRepository customerRepository, RentalRepository rentalRepository, VehicleRepository vehicleRepository) {
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public RentalOutputDto createRental(RentalDto rentalDto) {
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

        RentalOutputDto rentalOutputDto = new RentalOutputDto(savedRental.getId(), savedRental.getVehicle().getId(), savedRental.getCustomer().getId(), savedRental.getStartDate(), savedRental.getEndDate(), savedRental.getTotalCost());

        return rentalOutputDto;
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
    public RentalOutputDto getRentalById(Long id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isEmpty()) {
            throw new RentalNotFoundException(id, "Rental not found");
        }
        Rental thisRental = rental.get();
        return new RentalOutputDto(thisRental.getId(), thisRental.getVehicle().getId(), thisRental.getCustomer().getId(), thisRental.getStartDate(), thisRental.getEndDate(), thisRental.getTotalCost());
    }

    @Override
    public List<RentalOutputDto> getAllOngoingRental() {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        List<Rental> ongoingRentalList = rentalRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(today,today);
        List<RentalOutputDto> rentalOutputDtoList = new java.util.ArrayList<>();
        for(Rental rental: ongoingRentalList){
            rentalOutputDtoList.add(new RentalOutputDto(rental.getId(), rental.getVehicle().getId(), rental.getCustomer().getId(), rental.getStartDate(), rental.getEndDate(), rental.getTotalCost()));
        }
        return rentalOutputDtoList;
    }

    @Override
    public List<RentalOutputDto> getRentalHistoryByCustomerId(Long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException(customerId, "Customer not found");
        }

        List<Rental> rentalHistory = rentalRepository.findByCustomerId(customerId);
        List<RentalOutputDto> rentalOutputDtoList = new ArrayList<>();
        for(Rental rental: rentalHistory){
            rentalOutputDtoList.add(new RentalOutputDto(rental.getId(), rental.getVehicle().getId(), rental.getCustomer().getId(), rental.getStartDate(), rental.getEndDate(), rental.getTotalCost()));
        }
        return rentalOutputDtoList;
    }

    @Override
    public List<RentalOutputDto> getRentalHistoryByVehicleId(Long vehicleId){
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);

        if(optionalVehicle.isEmpty()){
            throw new VehicleNotFoundException(vehicleId, "Vehicle not found");
        }

        List<Rental> rentalHistoryList = rentalRepository.findByVehicleId(vehicleId);
        List<RentalOutputDto> rentalOutputDtoList = new ArrayList<>();
        for(Rental rental: rentalHistoryList){
            rentalOutputDtoList.add(new RentalOutputDto(rental.getId(), rental.getVehicle().getId(), rental.getCustomer().getId(), rental.getStartDate(), rental.getEndDate(), rental.getTotalCost()));
        }
        return rentalOutputDtoList;
    }

    @Override
    public List<RentalOutputDto> getReservationByVehicleId(Long id) {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        Vehicle vehicle = vehicleOptional.orElseThrow(() -> new VehicleNotFoundException(id, "Invalid Id"));

        List<Rental> reservationHistory = rentalRepository.findAllByStartDateAfterAndVehicle(today,vehicle);
        List<RentalOutputDto> rentalOutputDtoList = new ArrayList<>();
        for(Rental rental: reservationHistory){
            rentalOutputDtoList.add(new RentalOutputDto(rental.getId(), rental.getVehicle().getId(), rental.getCustomer().getId(), rental.getStartDate(), rental.getEndDate(), rental.getTotalCost()));
        }
        return rentalOutputDtoList;
    }

    @Override
    public List<RentalOutputDto> getReservationByCustomerId(Long id) {
        Calendar calender = Calendar.getInstance();
        Date today = calender.getTime();
        Optional<Customer> customerOptional = customerRepository.findById(id);
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException(id, "Invalid Id"));

        List<Rental> reservationList = rentalRepository.findAllByStartDateAfterAndCustomer(today,customer);
        List<RentalOutputDto> rentalOutputDtoList = new ArrayList<>();
        for(Rental rental: reservationList){
            rentalOutputDtoList.add(new RentalOutputDto(rental.getId(), rental.getVehicle().getId(), rental.getCustomer().getId(), rental.getStartDate(), rental.getEndDate(), rental.getTotalCost()));
        }
        return rentalOutputDtoList;
    }

    @Override
    public Double getRentalCost(CostDto costDto) {

        Long id = costDto.getId();
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        if(vehicleOptional.isEmpty()){
            throw new VehicleNotFoundException(id, "Vehicle not found");
        }

        Vehicle vehicle = vehicleOptional.get();
        Date startDate = costDto.getStartDate();
        Date endDate = costDto.getEndDate();

        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Long numberOfDays = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

        Double cost = 0D;
        Double twoWheelerPrice = 100D;
        Double fourWheelerPrice = 200D;

        if (vehicle.getVehicleType().name().equals("TWO_WHEELER")) {
            cost += numberOfDays * twoWheelerPrice;
        } else {
            cost += numberOfDays * fourWheelerPrice;
        }

        return cost;
    }



    @Override
    public RentalOutputDto cancelRentalById(Long id) {
        Optional<Rental> thisRental = rentalRepository.findById(id);
        if(thisRental.isEmpty()){
            throw new RentalNotFoundException(id, "Rental not found");
        }
        rentalRepository.deleteById(id);
        return new RentalOutputDto(thisRental.get().getId(), thisRental.get().getVehicle().getId(), thisRental.get().getCustomer().getId(), thisRental.get().getStartDate(), thisRental.get().getEndDate(), thisRental.get().getTotalCost());
    }
}
