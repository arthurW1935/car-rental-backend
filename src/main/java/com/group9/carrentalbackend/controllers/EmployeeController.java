package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.dtos.EmployeeDto;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        LOGGER.info("EmployeeController.getEmployeeById: id: {}", id);
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("")
    public List<EmployeeDto> getEmployees(){
        LOGGER.info("EmployeeController.getEmployees");
        return employeeService.getEmployees();
    }

    @PostMapping("")
    public EmployeeDto createEmployee(@RequestBody Employee employee){
        LOGGER.info("EmployeeController.createEmployee: employee: {}", employee);
        return employeeService.createEmployee(employee);
    }

    @PutMapping("")
    public EmployeeDto updateEmployee(@RequestBody Employee employee) {
        LOGGER.info("EmployeeController.updateEmployee: employee: {}", employee);
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public EmployeeDto deleteEmployee(@PathVariable Long id) {
        LOGGER.info("EmployeeController.deleteEmployee: id: {}", id);
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/branch/{id}")
    public List<EmployeeDto> getEmployeesByBranch(@PathVariable Long id){
        LOGGER.info("EmployeeController.getEmployeesByBranch: id: {}", id);
        return employeeService.getEmployeesByBranch(id);
    }
}
