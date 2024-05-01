package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.services.BranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable Long id){
        return branchService.getBranchById(id);
    }

    @GetMapping
    public List<Branch> getAllBranch(){
        return branchService.getAllBranch();
    }

    @PostMapping
    public Branch createBranch(@RequestBody Branch branch){
        return branchService.createBranch(branch);
    }

    @PutMapping
    public Branch updateBranch(@RequestBody Branch branch){
        return branchService.updateBranch(branch);
    }

    @DeleteMapping("/{id}")
    public Branch deleteBranchById(@PathVariable Long id){
        return branchService.deleteBranch(id);
    }

}
