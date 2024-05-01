package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.BranchDto;
import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.exceptions.EmployeeNotFoundException;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.models.Employee;
import com.group9.carrentalbackend.repositories.BranchRepository;
import com.group9.carrentalbackend.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public BranchDto getBranchById(Long id) throws BranchNotFoundException {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty()) {
            throw new BranchNotFoundException(id, "Branch not found");
        }
        Branch thisBranch = branch.get();
        return new BranchDto(thisBranch.getId(), thisBranch.getLocation(), thisBranch.getManager().getId(), thisBranch.getPhoneNumber(), thisBranch.getEmail());
    }

    @Override
    public List<BranchDto> getAllBranch() {
        List<Branch> branchList = branchRepository.findAll();
        List<BranchDto> branchDtoList = new ArrayList<>();
        for(Branch branch: branchList){
            branchDtoList.add(new BranchDto(branch.getId(), branch.getLocation(), branch.getManager().getId(), branch.getPhoneNumber(), branch.getEmail()));
        }
        return branchDtoList;
    }

    @Override
    public BranchDto createBranch(Branch branch) {
        if (branch.getManager().getId() == null) {
            Employee manager = branch.getManager();
            manager.setBranch(null);
            Employee savedEmployee = employeeRepository.save(manager);
            branch.setManager(savedEmployee);
        }
        else{
            Optional<Employee> manager = employeeRepository.findById(branch.getManager().getId());
            if(manager.isEmpty()){
                throw new EmployeeNotFoundException(branch.getManager().getId(), "Category not found");
            }
            branch.setManager(manager.get());
        }
        Branch newBranch = branchRepository.save(branch);
        newBranch.getManager().setBranch(newBranch);
        employeeRepository.save(newBranch.getManager());
        return new BranchDto(newBranch.getId(), newBranch.getLocation(), newBranch.getManager().getId(), newBranch.getPhoneNumber(), newBranch.getEmail());
    }

    @Override
    public BranchDto updateBranch(Branch branch) {
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

        Branch thisBranch = branchRepository.save(branch);
        return new BranchDto(thisBranch.getId(), thisBranch.getLocation(), thisBranch.getManager().getId(), thisBranch.getPhoneNumber(), thisBranch.getEmail());
    }

    @Override
    public BranchDto deleteBranch(Long id)  {
        Optional<Branch> existingBranch = branchRepository.findById(id);

        if(existingBranch.isEmpty()){
            throw new BranchNotFoundException(id, "Branch not found");
        }

        if(employeeRepository.findAllByBranchId(existingBranch.get().getId()).size() > 1 ){
            throw new BranchNotFoundException(id, "Branch has more than one employee");
        }

        existingBranch.get().getManager().setBranch(null);
        employeeRepository.save(existingBranch.get().getManager());
        branchRepository.delete(existingBranch.get());

        Branch deletedBranch = existingBranch.get();
        return new BranchDto(deletedBranch.getId(), deletedBranch.getLocation(), deletedBranch.getId(), deletedBranch.getPhoneNumber(), deletedBranch.getEmail());
    }
}
