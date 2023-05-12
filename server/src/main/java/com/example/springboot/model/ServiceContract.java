package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_contract")
public class ServiceContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceContractID;

    @Column(name = "startDate", columnDefinition = "DATE")
    private Date startDate;

    @Column(name = "description", columnDefinition = "NVARCHAR(255)")
    private String description;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_serviceContract_to_Company")
            , name = "companyID", referencedColumnName = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_serviceContract_to_service")
            , name = "serviceID", referencedColumnName = "serviceID")
    private ServiceEntity service;


}
