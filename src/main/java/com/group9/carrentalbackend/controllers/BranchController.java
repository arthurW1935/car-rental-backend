package com.group9.carrentalbackend.controllers;

import com.group9.carrentalbackend.models.Branch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @GetMapping
    public Branch getBranchById(){
        return null;
    }

    @GetMapping
    public List<Branch> getAllBranch(){
        return null;
    }

    @PostMapping
    public Branch createBranch(@RequestBody Branch branch){
        return null;
    }

    @PutMapping
    public Branch updateBranch(@RequestBody Branch branch){
        return null;
    }

    @DeleteMapping
    public Branch deleteBranch(){
        return null;
    }

}
