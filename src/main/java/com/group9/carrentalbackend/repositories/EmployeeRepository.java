package com.group9.carrentalbackend.repositories;

import com.group9.carrentalbackend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    Employee save(Employee employee);

    @Override
    Optional<Employee> findById(Long id);

    @Override
    List<Employee> findAll();

    @Override
    void deleteById(Long id);

    List<Employee> findByBranch(Long branchId);
}
