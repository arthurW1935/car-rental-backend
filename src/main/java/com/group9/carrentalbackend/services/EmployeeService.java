package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.EmployeeDeleteException;
import com.group9.carrentalbackend.exceptions.EmployeeUpdateException;
import com.group9.carrentalbackend.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long id);
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee) throws EmployeeUpdateException;
    Employee deleteEmployee(Long id) throws EmployeeDeleteException;
    List<Employee> getEmployeesByBranch(Long branchId);
}