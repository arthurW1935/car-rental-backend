package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.dtos.EmployeeDto;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("")
    public List<EmployeeDto> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("")
    public EmployeeDto createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("")
    public EmployeeDto updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public EmployeeDto deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/branch/{id}")
    public List<EmployeeDto> getEmployeesByBranch(@PathVariable Long id){
        return employeeService.getEmployeesByBranch(id);
    }
}
