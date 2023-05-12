package com.example.springboot.DTO.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateDTO {

    private String companyName;
    private String taxCode;
    private String phoneNumber;

}
