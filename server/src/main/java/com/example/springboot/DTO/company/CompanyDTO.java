package com.example.springboot.DTO.company;

import com.example.springboot.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private int companyID;
    private String companyName;
    private String taxCode;
    private String phoneNumber;
    public CompanyDTO(Company company) {
        this.setCompanyID(company.getCompanyID());
        this.setCompanyName(company.getCompanyName());
        this.setTaxCode(company.getTaxCode());
        this.setPhoneNumber(company.getPhoneNumber());
    }
}
