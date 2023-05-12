package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees_building")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private int employeeID;

    @Column(name = "name", columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(name = "DOB", columnDefinition = "DATE")
    private Date DOB;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    private String address;

    @Column(name = "phoneNumber", columnDefinition = "NVARCHAR(255)")
    private String phoneNumber;

    @Column(name = "position", columnDefinition = "NVARCHAR(255)")
    private String position;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_employeeBuilding_to_salary")
            , name = "salaryID", referencedColumnName = "salaryID")
    private Salary salary;
}
