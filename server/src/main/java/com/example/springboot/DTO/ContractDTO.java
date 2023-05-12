package com.example.springboot.DTO;

import com.example.springboot.DTO.company.CompanyDTO;
import com.example.springboot.Mapper.ConvertDateToDateForm;
import com.example.springboot.model.Company;
import com.example.springboot.model.Contract;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO  extends  ConvertDateToDateForm{


    private int contractID;


    private String rentedDate;

    private String expiredDate;

    private double rentedArea;

    private String description;

    private int isCanceled;

    private CompanyDTO companyDTO;

    private FloorDTO floorDTO;

    public ContractDTO(Contract contract){
        this.setContractID(contract.getContractID());
        this.setRentedDate(super.convertDateToDate(contract.getRentedDate()));
        this.setExpiredDate(super.convertDateToDate(contract.getExpiredDate()));
        this.setRentedArea(contract.getRentedArea());
        this.setDescription(contract.getDescription());
        this.setIsCanceled(contract.getIsCanceled());
        this.setCompanyDTO(new CompanyDTO(contract.getCompany()));
        this.setFloorDTO(new FloorDTO(contract.getFloor()));
    }

}
