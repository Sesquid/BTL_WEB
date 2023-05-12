package com.example.springboot.DTO;

import com.example.springboot.model.ServiceEntity;
import lombok.Data;

@Data
public class ServiceDTO {

    private int serviceID;

    private String name;

    private String type;

    private double price;

    private int required;

    public ServiceDTO(ServiceEntity service){
        this.setServiceID(service.getServiceID());
        this.setName(service.getName());
        this.setType(service.getType());
        this.setPrice(service.getPrice());
        this.setRequired(service.getRequired());
    }
}
