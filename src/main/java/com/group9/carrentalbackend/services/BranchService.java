package com.group9.carrentalbackend.services;

import com.group9.carrentalbackend.exceptions.BranchNotFoundException;
import com.group9.carrentalbackend.models.Branch;

import java.util.List;

public interface BranchService {
    public Branch getBranchById(Long id) ;
    public List<Branch> getAllBranch();
    public Branch createBranch(Branch branch);
    public Branch updateBranch(Branch branch);
    public Branch deleteBranch(Long id) ;
}
