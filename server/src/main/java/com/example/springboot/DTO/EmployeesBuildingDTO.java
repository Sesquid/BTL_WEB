package com.example.springboot.DTO;

import com.example.springboot.model.EmployeesBuilding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesBuildingDTO {
    private int employeeID;

    private String name;

    private Date DOB;

    private String address;

    private String phoneNumber;

    private String position;

    private int salaryID;
    public EmployeesBuildingDTO(EmployeesBuilding buildingEmployee){
        this.setEmployeeID(buildingEmployee.getEmployeeID());
        this.setName(buildingEmployee.getName());
        this.setDOB(buildingEmployee.getDOB());
        this.setAddress(buildingEmployee.getAddress());
        this.setPhoneNumber(buildingEmployee.getPhoneNumber());
        this.setPosition(buildingEmployee.getPosition());
        this.salaryID = buildingEmployee.getSalary().getSalaryID();
    }
}