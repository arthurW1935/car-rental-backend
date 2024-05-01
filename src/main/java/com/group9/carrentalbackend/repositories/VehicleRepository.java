package com.group9.carrentalbackend.repositories;

import com.group9.carrentalbackend.models.Status;
import com.group9.carrentalbackend.models.Type;
import com.group9.carrentalbackend.models.Vehicle;
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

    List<Vehicle> findAllByType(Type type);

    List<Vehicle> findAllByStatus(Status status);

}
