package com.group9.carrentalbackend.services;


import com.group9.carrentalbackend.exceptions.EmployeeNotFoundException;
import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfEmployeeService implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SelfEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(Long id) throws InvalidArgumentException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new InvalidArgumentException(id, "Employee not found");
        }
        return employee.get();
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) throws InvalidArgumentException {
        if(employee.getId()!=null){
            throw new InvalidArgumentException(employee.getId(), "Employee id should be null");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(employee.getId() , "Employee not found");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee deleteEmployee(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(id, "Employee not found with id: ");
        }
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(id, "Failed to delete employee with id: ");
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeesByBranch(Long branchId) {
        return employeeRepository.findByBranchId(branchId);
    }
}
