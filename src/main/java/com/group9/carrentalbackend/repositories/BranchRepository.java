package com.group9.carrentalbackend.repositories;


import com.group9.carrentalbackend.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Override
    Branch save(Branch branch);

    @Override
    Optional<Branch> findById(Long id);

    @Override
    List<Branch> findAll();

    @Override
    void deleteById(Long id);

}
