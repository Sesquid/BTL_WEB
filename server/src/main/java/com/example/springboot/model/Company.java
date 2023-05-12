package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"employeesCompany"})
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyID;

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<EmployeesCompany> employeesCompany;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "taxCode")
    private String taxCode;

    @Column(name = "phoneNumber")
    private String phoneNumber;

}
