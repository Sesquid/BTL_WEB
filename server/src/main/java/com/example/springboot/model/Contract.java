package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractID")
    private int contractID;

    @Column(name = "rentedDate", columnDefinition = "DATE")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date rentedDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "expiredDate", columnDefinition = "DATE")
    private Date expiredDate;

    @Column(name = "rentedArea")
    private double rentedArea;

    @Column(name = "description")
    private String description;

    @Column(name = "isCanceled")
    private int isCanceled;

    // moi cong ty chi co 1 hop dong
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_contract_to_company")
            , name = "company_id", referencedColumnName = "company_id")
    private Company company;

    // 1 tang thi co the co nhieu hop dong
    // 1 hop dong thi cho 1 tang
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_contract_to_floor")
            , name = "floorID", referencedColumnName = "floorID")
    private Floor floor;
}

