package com.group9.carrentalbackend.services;




import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(Long id);
    List<Employee> getEmployees();
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee deleteEmployee(Long id);
    List<Employee> getEmployeesByBranch(Long branchId);
}