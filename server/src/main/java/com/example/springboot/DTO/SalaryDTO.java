package com.example.springboot.DTO;

import com.example.springboot.model.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    private int salaryID;

    private double salary;

    private int level;

    public SalaryDTO(Salary salary){
        this.setSalaryID(salary.getSalaryID());
        this.setSalary(salary.getSalary());
        this.setLevel(salary.getLevel());
    }
}
