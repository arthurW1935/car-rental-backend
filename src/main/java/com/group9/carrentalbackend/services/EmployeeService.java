package com.group9.carrentalbackend.services;




import com.group9.carrentalbackend.dtos.EmployeeDto;
import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.models.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getEmployees();
    EmployeeDto createEmployee(Employee employee);
    EmployeeDto updateEmployee(Employee employee);
    EmployeeDto deleteEmployee(Long id);
    List<EmployeeDto> getEmployeesByBranch(Long branchId);
}