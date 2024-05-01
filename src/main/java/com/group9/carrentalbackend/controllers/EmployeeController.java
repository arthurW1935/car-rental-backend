package com.group9.carrentalbackend.controllers;

import java.util.List;

import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return null;
    }

    @GetMapping("")
    public List<Employee> getEmployees(){
        return null;
    }

    @PostMapping("")
    public Employee createEmployee(@RequestBody Employee employee){
        return null;
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee){
        return null;
    }

    @DeleteMapping("{id}")
    public Employee deleteEmployee(@PathVariable Long id){
        return null;
    }

    @GetMapping("/{branchId}")
    public List<Employee> getEmployeesByBranch(@PathVariable Long branchId){
        return null;
    }
}
