package com.group9.carrentalbackend.repositories;

import com.group9.carrentalbackend.models.Vehicle;
import com.group9.carrentalbackend.models.VehicleStatus;
import com.group9.carrentalbackend.models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Override
    Vehicle save(Vehicle vehicle);

    @Override
    Optional<Vehicle> findById(Long id);

    @Override
    List<Vehicle> findAll();

    @Override
    void deleteById(Long id);

    List<Vehicle> findAllByVehicleType(VehicleType type);

    List<Vehicle> findAllByVehicleStatus(VehicleStatus status);

}
