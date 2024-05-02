package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.dtos.BranchDto;
import com.group9.carrentalbackend.models.Branch;
import com.group9.carrentalbackend.services.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping("/{id}")
    public BranchDto getBranchById(@PathVariable Long id){
        LOGGER.info("BranchController.getBranchById: id: {}", id);
        return branchService.getBranchById(id);
    }

    @GetMapping
    public List<BranchDto> getAllBranch(){
        LOGGER.info("BranchController.getAllBranch");
        return branchService.getAllBranch();
    }

    @PostMapping
    public BranchDto createBranch(@RequestBody Branch branch){
        LOGGER.info("BranchController.createBranch: branch: {}", branch);
        return branchService.createBranch(branch);
    }

    @PutMapping
    public BranchDto updateBranch(@RequestBody Branch branch){
        LOGGER.info("BranchController.updateBranch: branch: {}", branch);
        return branchService.updateBranch(branch);
    }

    @DeleteMapping("/{id}")
    public BranchDto deleteBranchById(@PathVariable Long id){
        LOGGER.info("BranchController.deleteBranchById: id: {}", id);
        return branchService.deleteBranch(id);
    }

}
