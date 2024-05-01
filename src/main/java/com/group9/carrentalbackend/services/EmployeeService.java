package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

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
            return null;
        }

        @Override
        public List<Employee> getEmployees() {
            return null;
        }

        @Override
        public Employee createEmployee(Employee employee) {
            return null;
        }

        @Override
        public Employee updateEmployee(Employee employee) {
            return null;
        }

        @Override
        public void deleteEmployee(Long id) {

        }

        @Override
        public List<Employee> getEmployeesByBranch(Long branchId) {
            return null;
        }
    }
}
