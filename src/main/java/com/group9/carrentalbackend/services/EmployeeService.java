package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getEmployeesByBranch(Long branchId);

    @Service
    class EmployeeServiceImpl implements EmployeeService {

        @Override
        public Employee getEmployeeById(Long id) {
            Optional<Employee> employee = employeeRepository.findById(id);
            if(employee.isEmpty()){
                return null; // throw exception
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
        public Employee updateEmployee(Employee employee) {
            Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
            if(existingEmployee.isEmpty()){
                return null; // throw exception
            }
            return employeeRepository.save(employee);
        }

        @Override
        public void deleteEmployee(Long id) {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if(existingEmployee.isEmpty()){
                return; // throw exception
            }
            employeeRepository.deleteById(id);
        }

        @Override
        public List<Employee> getEmployeesByBranch(Long branchId) {
            return employeeRepository.findByBranchId(branchId);
        }
    }
}
