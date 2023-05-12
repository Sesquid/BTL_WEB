package com.example.springboot.DTO.employees_company;

import com.example.springboot.model.EmployeesCompany;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesCompanyDTO {
    private String employeeID;
    private String employeeName;
    private Date date;
    private String phoneNumber;
    private Integer companyID;
    public EmployeesCompanyDTO(EmployeesCompany employeesCompany) {
        this.employeeID = employeesCompany.getEmployeeID();
        this.employeeName = employeesCompany.getEmployeeName();
        this.date = employeesCompany.getDate();
        this.phoneNumber = employeesCompany.getPhoneNumber();
        this.companyID = employeesCompany.getCompany().getCompanyID();
    }
}
