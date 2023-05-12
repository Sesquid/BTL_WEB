package com.example.springboot.DTO;


import com.example.springboot.DTO.company.CompanyDTO;
import com.example.springboot.Mapper.ConvertDateToDateForm;
import com.example.springboot.model.ServiceContract;
import com.example.springboot.model.ServiceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceContractDTO extends ConvertDateToDateForm {
    private int serviceContractID;

    private String startDate;

    private String description;

    private CompanyDTO companyDTO;

    private ServiceDTO serviceDTO;

    // con currentPrice la chuc nang nang cao se can den khi tinh bill hang thang

    public ServiceContractDTO(ServiceContract serviceContract){
        this.setServiceContractID(serviceContract.getServiceContractID());
        this.setDescription(serviceContract.getDescription());
        this.setStartDate(super.convertDateToDate(serviceContract.getStartDate()));
        this.setCompanyDTO(new CompanyDTO(serviceContract.getCompany()));
        this.setServiceDTO(new ServiceDTO(serviceContract.getService()));
    }
}
