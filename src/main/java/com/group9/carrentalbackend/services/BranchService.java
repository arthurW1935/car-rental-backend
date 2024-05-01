package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.dtos.BranchDto;
import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.models.Branch;

import java.util.List;

public interface BranchService {
    public BranchDto getBranchById(Long id) ;
    public List<BranchDto> getAllBranch();
    public BranchDto createBranch(Branch branch);
    public BranchDto updateBranch(Branch branch);
    public BranchDto deleteBranch(Long id) ;
}
