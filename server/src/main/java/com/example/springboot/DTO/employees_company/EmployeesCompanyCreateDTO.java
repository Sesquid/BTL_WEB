package com.example.springboot.DTO.employees_company;

import com.example.springboot.model.EmployeesCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesCompanyCreateDTO {
    private String employeeID;
    private String employeeName;
    private Date date;
    private String phoneNumber;
    private String companyID;
}
