package com.group9.carrentalbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {
    private Long id;
    private String location;
    private Long managerId;
    private String phoneNumber;
    private String email;
}
