package com.group9.carrentalbackend.services;


import com.group9.carrentalbackend.dtos.EmployeeDto;
import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.exceptions.EmployeeNotFoundException;
import com.group9.carrentalbackend.exceptions.InvalidArgumentException;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.repositories.BranchRepository;
import com.group9.carrentalbackend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SelfEmployeeService implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public SelfEmployeeService(EmployeeRepository employeeRepository,
                               BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws InvalidArgumentException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new InvalidArgumentException("Employee not found with id: " + id);
        }
        Employee thisEmployee = employee.get();
        Long branchId = -1L;
        if(thisEmployee.getBranch()!=null) branchId = thisEmployee.getBranch().getId();
        return new EmployeeDto(thisEmployee.getId(), thisEmployee.getName(), thisEmployee.getPhoneNumber(), thisEmployee.getEmail(), thisEmployee.getJoiningDate(), branchId);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList =  new ArrayList<>();
        for(Employee employee: employeeList){
            Long branchId = -1L;
            if(employee.getBranch()!=null) branchId = employee.getBranch().getId();
            employeeDtoList.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getPhoneNumber(), employee.getEmail(), employee.getJoiningDate(), branchId));
        }
        return employeeDtoList;
    }

    @Override
    public EmployeeDto createEmployee(Employee employee) throws InvalidArgumentException {
        if(employee.getId()!=null){
            throw new InvalidArgumentException("Employee id should be null");
        }
        Employee thisEmployee = employeeRepository.save(employee);

        Long branchId = -1L;
        if(thisEmployee.getBranch()!=null) branchId = thisEmployee.getBranch().getId();
        return new EmployeeDto(thisEmployee.getId(), thisEmployee.getName(), thisEmployee.getPhoneNumber(), thisEmployee.getEmail(), thisEmployee.getJoiningDate(), branchId);
    }

    @Override
    public EmployeeDto updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
        if (existingEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(employee.getId() , "Employee not found");
        }
        if(employee.getBranch()!=null && employee.getBranch().getId()!=null){
            Optional<Branch> branch = branchRepository.findById(employee.getBranch().getId());
            if(branch.isEmpty()){
                throw new BranchNotFoundException(employee.getBranch().getId(), "Manager not found");
            }
            employee.setBranch(branch.get());
        }
        else{
            throw new InvalidArgumentException("Branch id should not be null");
        }

        Employee thisEmployee = employeeRepository.save(employee);
        Long branchId = -1L;
        if(thisEmployee.getBranch()!=null) branchId = thisEmployee.getBranch().getId();
        return new EmployeeDto(thisEmployee.getId(), thisEmployee.getName(), thisEmployee.getPhoneNumber(), thisEmployee.getEmail(), thisEmployee.getJoiningDate(), branchId);
    }

    @Override
    public EmployeeDto deleteEmployee(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(id, "Employee not found");
        }
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(id, "Employee is still a branch manager. Please assign a new manager or delete the branch first.");
        }
        Employee thisEmployee = existingEmployee.get();
        Long branchId = -1L;
        if(thisEmployee.getBranch()!=null) branchId = thisEmployee.getBranch().getId();
        return new EmployeeDto(thisEmployee.getId(), thisEmployee.getName(), thisEmployee.getPhoneNumber(), thisEmployee.getEmail(), thisEmployee.getJoiningDate(), branchId);
    }

    @Override
    public List<EmployeeDto> getEmployeesByBranch(Long branchId) {
        List<Employee> employeeList = employeeRepository.findAllByBranchId(branchId);
        List<EmployeeDto> employeeDtoList =  new ArrayList<>();

        for(Employee employee: employeeList){
            employeeDtoList.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getPhoneNumber(), employee.getEmail(), employee.getJoiningDate(), employee.getBranch().getId()));
        }
        return employeeDtoList;
    }
}
