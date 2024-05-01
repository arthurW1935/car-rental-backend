package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.EmployeeDeleteException;
import com.group9.carrentalbackend.exceptions.EmployeeNotFoundException;
import com.group9.carrentalbackend.exceptions.EmployeeUpdateException;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee getEmployeeById(Long id);
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee) throws EmployeeUpdateException;
    void deleteEmployee(Long id) throws EmployeeDeleteException;
    List<Employee> getEmployeesByBranch(Long branchId);

    @Service
    class EmployeeServiceImpl implements EmployeeService {

        private final EmployeeRepository employeeRepository;
        @Autowired
        public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
            this.employeeRepository = employeeRepository;
        }

        @Override
        public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isEmpty()) {
                throw new EmployeeNotFoundException("Employee not found with id: " + id);
            }
            return employee.get();
        }

        @Override
        public List<Employee> getEmployees() {
            return employeeRepository.findAll();
        }

        @Override
        public Employee createEmployee(Employee employee) {
            return employeeRepository.save(employee);
        }

        @Override
        public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException, EmployeeUpdateException {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if (existingEmployee.isEmpty()) {
                throw new EmployeeNotFoundException("Employee not found with id: " + employee.getId());
            }
            try {
                return employeeRepository.save(employee);
            } catch (Exception e) {
                throw new EmployeeUpdateException("Failed to update employee with id: " + employee.getId(), e);
            }
        }

        @Override
        public void deleteEmployee(Long id) throws EmployeeNotFoundException, EmployeeDeleteException {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if (existingEmployee.isEmpty()) {
                throw new EmployeeNotFoundException("Employee not found with id: " + id);
            }
            try {
                employeeRepository.deleteById(id);
            } catch (Exception e) {
                throw new EmployeeDeleteException("Failed to delete employee with id: " + id, e);
            }
        }

        @Override
        public List<Employee> getEmployeesByBranch(Long branchId) {
            return employeeRepository.findByBranchId(branchId);
        }
    }
}