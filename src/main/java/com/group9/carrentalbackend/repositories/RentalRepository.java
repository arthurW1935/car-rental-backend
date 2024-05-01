package com.group9.carrentalbackend.repositories;

import com.group9.carrentalbackend.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Override
    Rental save(Rental rental);

    @Override
    Optional<Rental> findById(Long id);

    @Override
    List<Rental> findAll();

    @Override
    void deleteById(Long id);


}
