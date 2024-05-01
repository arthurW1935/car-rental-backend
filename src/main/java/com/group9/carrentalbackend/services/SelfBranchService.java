package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.exceptions.EmployeeNotFoundException;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.repositories.BranchRepository;
import com.group9.carrentalbackend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfBranchService implements BranchService {

    private final BranchRepository branchRepository;
    private final EmployeeRepository employeeRepository;
    public SelfBranchService(BranchRepository branchRepository, EmployeeRepository employeeRepository){
        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Branch getBranchById(Long id) throws BranchNotFoundException {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty()) {
            throw new BranchNotFoundException(id, "Product not found");
        }
        return branch.get();
    }

    @Override
    public List<Branch> getAllBranch() {
        return branchRepository.findAll();
    }

    @Override
    public Branch createBranch(Branch branch) {
        if (branch.getManager().getId() == null) {
            Employee manager = branch.getManager();
            Employee savedEmployee = employeeRepository.save(manager);
            branch.setManager(savedEmployee);
        } else{
            Optional<Employee> manager = employeeRepository.findById(branch.getManager().getId());
            if(manager.isEmpty()){
                throw new EmployeeNotFoundException(branch.getManager().getId(), "Category not found");
            }
            branch.setManager(manager.get());
        } return branchRepository.save(branch);
    }

    @Override
    public Branch updateBranch(Branch branch) {
        Optional<Branch> existingBranch = branchRepository.findById(branch.getId());

        if(existingBranch.isEmpty()){
            throw new BranchNotFoundException(branch.getId(), "Branch not found");
        }

        if (branch.getManager().getId() == null) {
            Employee manager = branch.getManager();
            Employee savedEmployee = employeeRepository.save(manager);
            branch.setManager(savedEmployee);
        }
        else{
            Optional<Employee> manager = employeeRepository.findById(branch.getManager().getId());
            if(manager.isEmpty()){
                throw new EmployeeNotFoundException(branch.getManager().getId(), "Employee not found");
            }
            branch.setManager(manager.get());
        }

        return branchRepository.save(branch);
    }

    @Override
    public Branch deleteBranch(Long id)  {
        Optional<Branch> existingBranch = branchRepository.findById(id);

        if(existingBranch.isEmpty()){
            throw new BranchNotFoundException(id, "Branch not found");
        }

        branchRepository.delete(existingBranch.get());

        return existingBranch.get();
    }
}
