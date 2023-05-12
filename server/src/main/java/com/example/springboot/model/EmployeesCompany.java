package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesCompany {
    @Id
    private String employeeID;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_employees_to_company"),
            name = "company_id", referencedColumnName = "company_id")
    @JsonIgnore
    private Company company;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "dateOfBirth", columnDefinition = "DATE")
    private Date date;

    @Column(name = "phoneNumber")
    private String phoneNumber;
}
