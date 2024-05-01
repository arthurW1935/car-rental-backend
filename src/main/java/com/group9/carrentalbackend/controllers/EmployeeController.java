package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.exceptions.EmployeeDeleteException;
import com.group9.carrentalbackend.exceptions.EmployeeUpdateException;
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
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("")
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public Employee deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/branch/{id}")
    public List<Employee> getEmployeesByBranch(@PathVariable Long id){
        return employeeService.getEmployeesByBranch(id);
    }
}
